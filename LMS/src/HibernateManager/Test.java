package HibernateManager;

import Persistance.Book;
import Persistance.Member;

/**
 * Created by denislavrov on 9/2/14.
 */
public class Test {
    /**
     * BIG DEAL !!!!
     * methods in Session take arguments of serializable
     * which really is just the primary key in the database.
     * so just supplying the index is fine.
     */




    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();
        for (Object o : hm.listEntities(Book.class)){
            System.out.println(((Book) o).getTitle());
        }
        Member member = new Member();
        member.setFirstname("Denis");
        member.setLastname("Lavrov");
        hm.addEntity(member);
        Book book = new Book();
        book.setTitle("Hello World in Java");
        hm.addEntity(book);
    }
}
