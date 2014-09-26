package test.managers;

import persistance.Member;
import managers.search.SearchBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


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
        SearchBase<Member> memberSearch = new SearchBase<>(Member.class, "Tenis Lafrov");
        memberSearch.simpleSearch("firstname", "lastname");
        if (memberSearch.getResults().getResultCount() <= 0) throw new AssertionError();
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
