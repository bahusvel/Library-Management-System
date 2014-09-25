package managers.search;

import Persistance.Book;
import managers.HibernateManager;
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

    private static FullTextSession fullTextSession;

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
        fullTextSession = Search.getFullTextSession(HibernateManager.getSession());
        if (fullTextSession == null) {
            try {
                fullTextSession.createIndexer().startAndWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static List<String> bookSuggestions(final String input, final int results){
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

    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> newSearch(Class<E> entity, String input, String... fields){
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

    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> newFuzzySearch(Class<E> entity, String input, String... fields){
        SearchResults<E> results = new SearchResults<>(input);
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .fuzzy()
                .withThreshold(.7f)
                .onFields(fields)
                .matching(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
        results.populateResult((List<E>) ftq.list());
        return results;
    }

    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> newDynamicFuzzy(Class<E> entity, String input, int minResults, String... fields){
        SearchResults<E> results = new SearchResults<>(input);
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        int cresults;
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
            cresults = ftq.getResultSize();
            fuzzines -= 0.1f;
        }while(cresults < minResults && fuzzines > 0.2f);
        results.populateResult((List<E>) ftq.list());
        return results;
    }


    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> newWildcardSearch(Class<E> entity, String input, String field){
        SearchResults<E> results = new SearchResults<>(input);
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .keyword()
                .wildcard()
                .onField(field)
                .matching(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
        results.populateResult((List<E>) ftq.list());
        return results;
    }

    @SuppressWarnings("unchecked")
    public static <E> SearchResults<E> newPhraseSearch(Class<E> entity, String input, String field, int sloppiness){
        SearchResults<E> results = new SearchResults<>(input);
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
        Query query = qb
                .phrase()
                .withSlop(sloppiness)
                .onField(field)
                .sentence(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query,entity);
        results.populateResult((List<E>) ftq.list());
        return results;
    }
}
