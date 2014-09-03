package HibernateManager;

import Persistance.Book;

import java.util.ArrayList;
import java.util.Date;

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
            System.out.println(((Book) o).getAuthor());
        }
        Book book = new Book();
        book.setTitle("Hello World in Java");
        ArrayList<String> author = new ArrayList<>();
        author.add("Flying me");
        author.add("And drunk drunky");
        book.setAuthor(author);
        book.setReleaseDate(new Date(1409663906879L));
        hm.addEntity(book);
    }
}
