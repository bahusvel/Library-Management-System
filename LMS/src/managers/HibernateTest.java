package managers;

import Persistance.BookEntity;
import Persistance.Employee;
import Persistance.Member;
import managers.HibernateManager.AutoTransaction;

import java.util.ArrayList;

/**
 * Created by denislavrov on 9/2/14.
 */
public class HibernateTest {
    /**
     * BIG DEAL !!!!
     * methods in Session take arguments of serializable
     * which really is just the primary key in the database.
     * so just supplying the index is fine.
     */


    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();

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
}
