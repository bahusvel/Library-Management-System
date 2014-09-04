package HibernateManager;

import Persistance.Book;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchTest {
    public static void main(String[] args) {
        SearchManager sm = new SearchManager(HibernateManager.getSession());
        for (Object b : sm.search(Book.class,"Some","title")){
            System.out.println(((Book)b).getTitle());
        }
    }
}
