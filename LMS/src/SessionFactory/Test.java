package SessionFactory;

import Persistance.Book;


/**
 * Created by denislavrov on 9/2/14.
 */
public class Test {
    public static void main(String[] args) {
        HibernateManager hm = new HibernateManager();
        Book book = new Book();
        book.setTitle("Some Random Book im adding");
        hm.AddBook(book);

    }
}
