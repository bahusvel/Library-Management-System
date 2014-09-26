package managers.search.entities;


import managers.search.SearchBase;
import managers.search.SearchResults;
import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import persistance.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by denislavrov on 9/26/14.
 */
public class BookSearch extends SearchBase<Book> {
    private static final String TITLE_EDGE_NGRAM_INDEX = "edgeTitle";
    private static final String TITLE_NGRAM_INDEX = "ngramTitle";
    private static final int MAXRESULTS = 5;
    private static final Class<Book> ENTITY = Book.class;
    private boolean fasetsApplied = false;



    public BookSearch(String input) {
        super(ENTITY, input);
        facetingRequests =  new ArrayList<>();
        facetMap = new HashMap<>();
        buildCategoryFacet();
    }

    public void buildCategoryFacet(){
        facetingRequests.add(buildDiscreteFacet("categoryFacetingRequest","category"));
    }

    @Override
    public SearchResults<Book> getResults() {
        createFullTextQuery();
        if (!fasetsApplied) {
            setupFacets();
            fasetsApplied = true;
        }
        else applyFacets();
        retrieveFacets();
        createResults();
        facetMap.forEach((k,v) -> v.forEach(f-> System.out.println(f.getValue() + f.getCount())));
        return results;
    }

    @SuppressWarnings("unchecked")
    public List<String> getSuggestions(){
        QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(ENTITY).get();
        Query query = qb
                .phrase()
                .withSlop(10) // Has interesting effects !!
                .onField(TITLE_NGRAM_INDEX)
                .andField(TITLE_EDGE_NGRAM_INDEX)
                .boostedTo(5.0f)
                .sentence(input)
                .createQuery();
        FullTextQuery ftq = fullTextSession.createFullTextQuery(query, ENTITY);
        ftq.setProjection("store_title");
        ftq.setMaxResults(MAXRESULTS);
        List<String> ret = new ArrayList<>();
        ret.addAll(((List<Object[]>)ftq.list()).stream().map(oa -> (String) oa[0]).collect(Collectors.toList()));
        return ret;
    }
}
