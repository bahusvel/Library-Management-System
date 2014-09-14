package managers;

import Persistance.Book;
import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.search.*;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public <E> List<E> search(Class<E> entity, String input, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .onFields(fields)
                .matching(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query);
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
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> dynamicFuzzy(Class<E> entity,String input, int minResults, String... fields){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        int results;
        float fuzzines = 0.7f;
        FullTextQuery ftq;
        do {
            Query query = qb
                    .keyword()
                    .fuzzy()
                    .withThreshold(.7f)
                    .onFields(fields)
                    .matching(input)
                    .createQuery();
            ftq = fullTextSession.createFullTextQuery(query, entity);
            results = ftq.getResultSize();
            fuzzines -= 0.1f;
        }while(results < minResults && fuzzines > 0.2f);
        return (List<E>) ftq.list();
    }


    @SuppressWarnings("unchecked")
    public <E> List<E> wildcardSearch(Class<E> entity, String input, String field){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .wildcard()
                .onField(field)
                .matching(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
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
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
        return (List<E>) ftq.list();
    }

    @SuppressWarnings("unchecked")
    public List<String> bookSuggestions(final String input, final int results){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class).get();
        Query query = qb
                .phrase()
                .withSlop(10) // Has interesting effects !!
                .onField(TITLE_NGRAM_INDEX)
                .andField(TITLE_EDGE_NGRAM_INDEX)
                .boostedTo(5.0f)
                .sentence(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query, Book.class);
        ftq.setProjection("store_title");
        ftq.setMaxResults(results);
        List<String> ret = new ArrayList<>();
        ret.addAll(((List<Object[]>)ftq.list()).stream().map(oa -> (String) oa[0]).collect(Collectors.toList()));
        return ret;
    }
}
