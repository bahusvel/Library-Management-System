package test.managers;

import Persistance.Member;
import managers.HibernateManager;
import managers.SearchManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;


/**
 * SearchManager Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 22, 2014</pre>
 */
public class SearchManagerTest {

    @Before
    public void before() throws Exception {
        SearchManager.initIndex(HibernateManager.getSession());
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: search(Class<E> entity, String input, String... fields)
     */
    @Test
    public void testSearch() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: fuzzySearch(Class<E> entity, String input, String... fields)
     */
    @Test
    public void testFuzzySearch() throws Exception {

    }

    @Test
    public void testPhoneticSearch() throws Exception {
        assert !SearchManager.search(Member.class, "Tenis Lafrov", "firstname", "lastname").isEmpty();
    }

    /**
     * Method: dynamicFuzzy(Class<E> entity, String input, int minResults, String... fields)
     */
    @Test
    public void testDynamicFuzzy() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: wildcardSearch(Class<E> entity, String input, String field)
     */
    @Test
    public void testWildcardSearch() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: phraseSearch(Class<E> entity, String input, String field, int sloppiness)
     */
    @Test
    public void testPhraseSearch() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: bookSuggestions(final String input, final int results)
     */
    @Test
    public void testBookSuggestions() throws Exception {
//TODO: Test goes here...
    }


    /**
     * Method: initIndex()
     */
    @Test
    public void testInitIndex() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = SearchManager.getClass().getMethod("initIndex");
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

}
