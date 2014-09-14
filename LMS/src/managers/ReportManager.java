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

import java.util.Date;
import java.util.HashMap;

/**
 * Created by denislavrov on 9/13/14.
 */
public class ReportManager {
    public void bookReport() throws JRException, ClassNotFoundException {
        FastReportBuilder drb = new FastReportBuilder();
        DynamicReport dr = drb
                .addColumn("Title", "title", String.class.getName(), 30)
                .setTitle("This is a book report")
                .setSubtitle("This report was generated at " + new Date())
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


    public static void main(String[] args) {
        ReportManager rm = new ReportManager();
        try {
            rm.bookReport();
        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
