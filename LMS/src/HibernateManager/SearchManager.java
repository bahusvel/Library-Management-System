package HibernateManager;

import Persistance.Book;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

/**
 * Created by denislavrov on 9/4/14.
 */
public class SearchManager {
    private static final String TITLE_EDGE_NGRAM_INDEX = "edgeTitle";
    private static final String TITLE_NGRAM_INDEX = "ngramTitle";


    Session session;
    FullTextSession fullTextSession;
    public SearchManager(Session session){
        this.session = session;
        try {
            initIndex();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initIndex() throws InterruptedException {
        fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
    }


    @SuppressWarnings("unchecked")
    public <E> List<E> search(Class entity, String input, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .onFields(fields)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> fuzzySearch(Class<E> entity, String input, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .fuzzy()
                .withThreshold(.7f)
                .onFields(fields)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> dynamicFuzzy(Class<E> entity,String input, int minResults, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        int results = 0;
        float fuzzines = 0.7f;
        org.hibernate.Query ftq;
        do {
            Query query = qb
                    .keyword()
                    .fuzzy()
                    .withThreshold(.7f)
                    .onFields(fields)
                    .matching(input)
                    .createQuery();
            ftq = fullTextSession.createFullTextQuery(query, entity);
            results = ftq.list().size();
            fuzzines -= 0.1f;
        }while(results < minResults && fuzzines > 0.2f);
        return (List<E>) ftq.list();
    }


    @SuppressWarnings("unchecked")
    public <E> List<E> wildcardSearch(Class entity, String input, String field){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .wildcard()
                .onField(field)
                .matching(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> phraseSearch(Class<E> entity, String input, String field, int sloppiness){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .phrase()
                .withSlop(sloppiness)
                .onField(field)
                .sentence(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query,entity);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public List<Book> bookSuggestions(final String input, final int results){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        Query query = qb
                .phrase()
                .withSlop(2)
                .onField(TITLE_NGRAM_INDEX)
                .andField(TITLE_EDGE_NGRAM_INDEX)
                .boostedTo(5.0f)
                .sentence(input)
                .createQuery();
        org.hibernate.Query ftq = fullTextSession.createFullTextQuery(query, Book.class);
        ftq.setMaxResults(results);

        return (List<Book>) ftq.list();
    }
}
