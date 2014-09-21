package managers;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by denislavrov on 9/13/14.
 */
public class ReportManager {
    private static Style detailStyle = new Style("detail");
    private static Style headerStyle = new Style("header");
    private static Style headerVariables = new Style("headerVariables");
    private static Style groupVariables = new Style("groupVariables");
    private static Style titleStyle = new Style("titleStyle");
    private static Style importeStyle = new Style();
    private static Style oddRowStyle = new Style();
    static {
        headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
        headerStyle.setBackgroundColor(Color.gray);
        headerStyle.setTextColor(Color.black);
        headerStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);


        headerVariables.setFont(Font.ARIAL_BIG_BOLD);
        headerVariables.setBorderBottom(Border.THIN());
        headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        headerVariables.setVerticalAlign(VerticalAlign.TOP);
        headerVariables.setStretchWithOverflow(true);


        groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
        groupVariables.setTextColor(Color.BLUE);
        groupVariables.setBorderBottom(Border.THIN());
        groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
        groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);


        titleStyle.setFont(new Font(18,Font._FONT_VERDANA, true));

        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

        oddRowStyle.setBorder(Border.NO_BORDER());
        oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
    }

    @SuppressWarnings("unchecked")
    public static void bookReport(Date from, Date to) throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("Title", "title", String.class.getName(), 30)
                .setTitle("Book lending report.")
                .setSubtitle("All book lendings from " + from + " to " + to)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setQuery("SELECT b.title as title FROM Book as b", DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        /* This actually works but is a little silly
        HibernateManager hm = new HibernateManager();
        List<Magazine> ds = hm.listEntities(Magazine.class);
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), ds);
        */

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }

    @SuppressWarnings("unchecked")
    public static void booksInLease() throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("Title", "title", String.class.getName(), 30)
                .addColumn("First Name", "firstname", String.class.getName(), 30)
                .addColumn("Last Name", "lastname", String.class.getName(), 30)
                .setTitle("Book lending report.")
                .setSubtitle("All books currently leased.")
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setQuery(
                        "SELECT bl.bookEntity.book.title as title, m.firstname as firstname, m.lastname as lastname " +
                                "FROM BookLease as bl join bl.member as m " +
                                "WHERE bl.bookEntity.leased = true",
                        DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }

    @SuppressWarnings("unchecked")
    public static void activityReport(Date from, Date to) throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        String sFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
        String sTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();

        AbstractColumn columnStatus = ColumnBuilder.getNew()
                .setColumnProperty("status", String.class.getName())
                .setTitle("Status")
                .setWidth(15)
                .build();

        AbstractColumn columnOverdue = ColumnBuilder.getNew()
                .setColumnProperty("overdue", Integer.class.getName())
                .setTitle("Days Overdue")
                .setPattern("0 Day(s) Overdue")
                .setWidth(15)
                .build();

        AbstractColumn columnTitle = ColumnBuilder.getNew()
                .setColumnProperty("title", String.class.getName())
                .setTitle("Title")
                .setStyle(headerStyle)
                .setWidth(30)
                .build();

        AbstractColumn columnFirstName = ColumnBuilder.getNew()
                .setColumnProperty("firstname", String.class.getName())
                .setTitle("First Name")
                .setWidth(30)
                .build();

        AbstractColumn columnLastName = ColumnBuilder.getNew()
                .setColumnProperty("lastname", String.class.getName())
                .setTitle("Last Name")
                .setWidth(30)
                .build();

        AbstractColumn columnCharged = ColumnBuilder.getNew()
                .setColumnProperty("charged", Double.class.getName())
                .setTitle("Amount Charged")
                .setWidth(15)
                .build();

        DJGroup groupStatus = new GroupBuilder()
                .setCriteriaColumn((PropertyColumn) columnStatus)
                .addFooterVariable(columnCharged, DJCalculation.SUM, groupVariables)
                .addFooterVariable(columnTitle, DJCalculation.COUNT, groupVariables)
                .setGroupLayout(GroupLayout.VALUE_IN_HEADER)
                .build();

        DJGroup groupOverdue = new GroupBuilder()
                .setCriteriaColumn((PropertyColumn) columnOverdue)
                .setGroupLayout(GroupLayout.VALUE_IN_HEADER)
                .build();

        DJGroup groupTitle = new GroupBuilder()
                .setCriteriaColumn((PropertyColumn) columnTitle)
                .build();

        DynamicReport dr = drb
                .setTitle("Activity Report.")
                .setSubtitle("All book activity from " + sFrom + " to " + sTo)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .addColumn(columnStatus)
                .addColumn(columnOverdue)
                .addColumn(columnTitle)
                .addColumn(columnFirstName)
                .addColumn(columnLastName)
                .addColumn(columnCharged)
                .addGroup(groupStatus)
                .addGroup(groupOverdue)
                .addGroup(groupTitle)
                .addAutoText(AutoText.AUTOTEXT_PAGE_X_SLASH_Y, AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_RIGHT)
                .setQuery(
                        "SELECT case br.lost when true then 'LOST' else 'RETURNED' end as status," +
                                " cast((br.returnDate - br.dueDate) as int) as overdue," +
                                " br.book.title as title," +
                                " m.firstname as firstname," +
                                " m.lastname as lastname," +
                                " br.ammountCharged as charged" +
                                " FROM BookReturn as br join br.member as m" +
                                " WHERE br.returnDate between '" + sFrom + "' and '" + sTo + "'",
                        DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }

    public static void main(String[] args) {
        try {
            ZonedDateTime zonedDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).minusMonths(1);
            Instant instant = zonedDateTime.toLocalDateTime().toInstant(ZoneOffset.UTC);
            Date back = Date.from(instant);
            activityReport(back, new Date());
        } catch (JRException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
