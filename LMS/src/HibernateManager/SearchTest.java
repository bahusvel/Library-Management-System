package HibernateManager;

import Persistance.Member;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchTest {
    public static void main(String[] args) {
        SearchManager sm = new SearchManager(HibernateManager.getSession());
        for (Object b : sm.fuzzySearch(Member.class,"Lavrov","lastname")){
            System.out.println(b);
        }
    }
}
