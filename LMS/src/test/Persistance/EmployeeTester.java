package test.Persistance;

import managers.HibernateManager;
import org.junit.After;
import org.junit.Before;

/**
 * Book Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 15, 2014</pre>
 */
public class EmployeeTester {
    HibernateManager hm;

    @Before
    public void before() throws Exception {
        hm = new HibernateManager();
    }

    @After
    public void after() throws Exception {
    }

    /*
    @Test
    public void testAddBookEntities() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Book book = (Book) at.session.get(Book.class, 86009);
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            employee.addBookEntities(book, 5);
            at.tx.commit();
        }
    }

    @Test
    public void testAddItemEntities() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Item item = (Item) at.session.get(Item.class, 1);
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            employee.addItemEntities(item, 5);
            at.tx.commit();
        }
    }
    */

}
