package managers;

import ar.com.fdvs.dj.core.BarcodeTypes;
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


        titleStyle.setFont(new Font(18, Font._FONT_VERDANA, true));

        importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

        oddRowStyle.setBorder(Border.NO_BORDER());
        oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
    }

    @SuppressWarnings("unchecked")
    public static void memberReport() throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("First Name", "firstname", String.class.getName(), 30)
                .addColumn("Last Name", "lastname", String.class.getName(), 30)
                .addColumn("Books Leased in Total", "booksleased", Long.class.getName(), 30)
                .addColumn("Books in lease", "booksinlease", Long.class.getName(), 30)
                .addColumn("Books Lost", "bookslost", Long.class.getName(), 30)
                .addColumn("Items Leased in Total", "itemsleased", Long.class.getName(), 30)
                .addColumn("Items in lease", "itemsinlease", Long.class.getName(), 30)
                .addColumn("Items Lost", "itemslost", Long.class.getName(), 30)
                .setTitle("Members report.")
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .setPageSizeAndOrientation(Page.Page_A4_Landscape())
                .setQuery("SELECT m.firstname as firstname," +
                        " m.lastname as lastname," +
                        " (select count(*) from m.bookReturns) as booksleased," +
                        " (select count(*) from m.bookLeases) as booksinlease," +
                        " (select count(*) from m.bookReturns as br where br.lost is true) as bookslost," +
                        " (select count(*) from m.itemReturns) as itemsleased," +
                        " (select count(*) from m.itemLeases) as itemsinlease," +
                        " (select count(*) from m.itemReturns as ir where ir.lost is true) as itemslost" +
                        " from Member as m", DJConstants.QUERY_LANGUAGE_HQL)
                .build();

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


        drb.setTitle("Activity Report.")
                .setSubtitle("All book activity from " + sFrom + " to " + sTo)
                .setPrintBackgroundOnOddRows(true)
                .setUseFullPageWidth(true)
                .addColumn(columnStatus)
                .addColumn(columnOverdue)
                .addColumn(columnTitle)
                .addColumn(columnFirstName)
                .addColumn(columnLastName)
                .addColumn(columnCharged);
        DynamicReport dr = drb.addBarcodeColumn("Barcode", "isbn", String.class.getName(), BarcodeTypes.EAN128, false, 100, true, ImageScaleMode.FILL)
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
                                " br.ammountCharged as charged," +
                                " br.book.isbn as isbn" +
                                " FROM BookReturn as br join br.member as m" +
                                " WHERE br.returnDate between '" + sFrom + "' and '" + sTo + "'",
                        DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }


    public static void bookSummaryReport() throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("Year", "booky", Integer.class.getName(), 30)
                .addColumn("Month", "bookm", Integer.class.getName(), 30)
                .addColumn("Status", "status", String.class.getName(), 30)
                .addColumn("Count", "bookc", Long.class.getName(), 30)
                .addGroups(2)
                .setTitle("Book lending summary report.")
                .setUseFullPageWidth(true)
                .setQuery(
                        "select year(br.returnDate) as booky," +
                                "month(br.returnDate) as bookm," +
                                " case br.lost when true then 'LOST' else 'RETURNED' end as status," +
                                " count(*) as bookc" +
                                " from BookReturn as br" +
                                " group by year(br.returnDate), month(br.returnDate), (case br.lost when true then 'LOST' else 'RETURNED' end)",
                        DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }

    public static void itemSummaryReport() throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("Year", "itemy", Integer.class.getName(), 30)
                .addColumn("Month", "itemm", Integer.class.getName(), 30)
                .addColumn("Status", "status", String.class.getName(), 30)
                .addColumn("Count", "itemc", Long.class.getName(), 30)
                .addGroups(2)
                .setTitle("Item lending summary report.")
                .setUseFullPageWidth(true)
                .setQuery(
                        "select year(ir.returnDate) as itemy," +
                                "month(ir.returnDate) as itemm," +
                                " case ir.lost when true then 'LOST' else 'RETURNED' end as status," +
                                " count(*) as itemc" +
                                " from ItemReturn as ir" +
                                " group by year(ir.returnDate), month(ir.returnDate), (case ir.lost when true then 'LOST' else 'RETURNED' end)",
                        DJConstants.QUERY_LANGUAGE_HQL)
                .build();

        HashMap params = new HashMap();
        params.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, HibernateManager.getSession());
        JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), params);
        JasperViewer.viewReport(jp);
    }

    public static void main(String[] args) {

        ZonedDateTime zonedDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).minusMonths(1);
        Instant instant = zonedDateTime.toLocalDateTime().toInstant(ZoneOffset.UTC);
        Date back = Date.from(instant);
        try {
            booksInLease();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
