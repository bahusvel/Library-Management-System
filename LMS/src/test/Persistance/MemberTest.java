package test.Persistance;

import Persistance.Book;
import Persistance.BookEntity;
import Persistance.Employee;
import Persistance.Member;
import managers.HibernateManager;
import managers.HibernateManager.AutoTransaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * Member Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 15, 2014</pre>
 */
public class MemberTest {
    HibernateManager hm;

    @Before
    public void before() throws Exception {
        hm = new HibernateManager();
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: signIn(), signOut()
     */
    @Test
    public void testAuthentication() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Member member = (Member) at.session.get(Member.class, 1);
            int before = member.getVisits().size();
            member.signIn();
            member.signOut();
            int after = member.getVisits().size();
            assert before + 1 == after;
            at.tx.commit();
        }
    }

    /**
     * Method: leaseBook(Book book, Employee employee, Date until)
     */
    @Test
    public void testOutdatedLeaseBook() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Book book = (Book) at.session.get(Book.class, 86009);
            Member member = (Member) at.session.get(Member.class, 1);
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            member.signIn();
            Date date = Date.from(LocalDate.now().minusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(member.leaseBook(book, employee, date));
            member.signOut();
            at.tx.commit();
        }
    }

    @Test
    public void testLeaseBook() throws Exception {
        try (AutoTransaction at = hm.newAutoTransaction()) {
            Book book = (Book) at.session.get(Book.class, 86009);
            Member member = (Member) at.session.get(Member.class, 1);
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            member.signIn();
            System.out.println(member.leaseBook(book, employee, new Date()));
            member.signOut();
            at.tx.commit();
        }
    }

    @Test
    public void testReturnBook() throws Exception{
        try(AutoTransaction at = hm.newAutoTransaction()) {
            Member member = (Member) at.session.get(Member.class, 1);
            BookEntity bookEntity = new ArrayList<>(member.getBookLeases()).get(0).getBookEntity();
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            member.signIn();
            System.out.println(member.returnBook(bookEntity, employee));
            member.signOut();
            at.tx.commit();
        }
    }

    @Test
    public void testlooseBook() throws Exception{
        try(AutoTransaction at = hm.newAutoTransaction()) {
            Member member = (Member) at.session.get(Member.class, 1);
            BookEntity bookEntity = new ArrayList<>(member.getBookLeases()).get(0).getBookEntity();
            Employee employee = (Employee) at.session.get(Employee.class, 1);
            member.signIn();
            System.out.println(member.looseBook(bookEntity, employee));
            member.signOut();
            at.tx.commit();
        }
    }
}
