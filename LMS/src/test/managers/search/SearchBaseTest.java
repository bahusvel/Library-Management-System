package test.managers.search;

import managers.search.SearchBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistance.Book;

/**
* SearchBase Tester.
*
* @author <Authors name>
* @since <pre>Sep 26, 2014</pre>
* @version 1.0
*/
public class SearchBaseTest {

@Before
public void before() throws Exception {
}

@After
public void after() throws Exception {
}

    @Test
    public void testSimple() throws Exception {
        SearchBase<Book> bookSearch = new SearchBase<>(Book.class, "Hell");
        bookSearch.simpleSearch("title")
                .getResults()
                .getResultList()
                .forEach(System.out::println);
    }

}
