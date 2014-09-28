package test.managers.search;

import persistance.Book;
import managers.search.SearchBase;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

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
