package test.managers;

import managers.ReportManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * ReportManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 14, 2014</pre>
 */
public class ReportManagerTest {


    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: bookReport(Date from, Date to)
     */
    @Test
    public void testBookReport() throws Exception {
        ReportManager.bookReport(new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2014"), new Date());
        assert JOptionPane.showConfirmDialog(null, "Does it look right?") == 0;

    }

    /**
     * Method: booksInLease()
     */
    @Test
    public void testBooksInLease() throws Exception {
        ReportManager.booksInLease();
        assert JOptionPane.showConfirmDialog(null, "Does it look right?") == 0;
    }

    @Test
    public void testActivityReport() throws Exception{
        ZonedDateTime zonedDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).minusMonths(1);
        Instant instant = zonedDateTime.toLocalDateTime().toInstant(ZoneOffset.UTC);
        Date back = Date.from(instant);
        ReportManager.activityReport(back, new Date());
        assert JOptionPane.showConfirmDialog(null, "Does it look right?") == 0;
    }


}
