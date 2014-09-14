package test.managers;

import managers.ReportManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ReportManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 14, 2014</pre>
 */
public class ReportManagerTest {
    ReportManager rm;

    @Before
    public void before() throws Exception {
        rm = new ReportManager();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: bookReport(Date from, Date to)
     */
    @Test
    public void testBookReport() throws Exception {
        rm.bookReport(new SimpleDateFormat("dd/MM/yyyy").parse("14/08/2014"), new Date());
        assert JOptionPane.showConfirmDialog(null, "Does it look right?") == 0;

    }

    /**
     * Method: booksInLease()
     */
    @Test
    public void testBooksInLease() throws Exception {
        rm.booksInLease();
        assert JOptionPane.showConfirmDialog(null, "Does it look right?") == 0;
    }
}
