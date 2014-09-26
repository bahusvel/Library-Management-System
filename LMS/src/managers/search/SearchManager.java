package managers.search;

import managers.HibernateManager;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchManager {
    private static final String TITLE_EDGE_NGRAM_INDEX = "edgeTitle";
    private static final String TITLE_NGRAM_INDEX = "ngramTitle";

    protected static FullTextSession fullTextSession;

    public static void initIndex(Session session) {
        if (fullTextSession == null) {
            fullTextSession = Search.getFullTextSession(session);
            try {
                fullTextSession.createIndexer().startAndWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initIndex() {
        if (fullTextSession == null) {
            fullTextSession = Search.getFullTextSession(HibernateManager.getSession());
            try {
                fullTextSession.createIndexer().startAndWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* Left for reference purposes only
    --OLD API--
    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> search(Class<E> entity, String input, String... fields){
        SearchResults<E> results = new SearchResults<>(input);
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .onFields(fields)
                .matching(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query);
        results.populateResult((List<E>) ftq.list());
        return results;
    }
    */
}
