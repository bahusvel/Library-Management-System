package test.Persistance;

import Persistance.Book;
import managers.HibernateManager;
import managers.HibernateManager.AutoTransaction;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * Book Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 15, 2014</pre>
 */
public class BookTest {
    HibernateManager hm;

    @Before
    public void before() throws Exception {
        hm = new HibernateManager();
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testAddEntities() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Book book = (Book) at.session.get(Book.class, 86009);
            book.addEntities(5);
            at.tx.commit();
        }
    }
}
