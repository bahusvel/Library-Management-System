package managers;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.view.JasperViewer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by denislavrov on 9/13/14.
 */
public class ReportManager {
    public void bookReport(Date from, Date to) throws JRException, ClassNotFoundException {
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

    public void booksInLease() throws JRException, ClassNotFoundException {
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


    public static void main(String[] args) throws ParseException {
        ReportManager rm = new ReportManager();
        try {
            rm.bookReport(new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2014"), new Date());
            //rm.booksInLease();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
