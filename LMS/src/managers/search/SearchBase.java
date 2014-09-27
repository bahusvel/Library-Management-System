package managers.search;

import org.apache.lucene.search.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetingRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by denislavrov on 9/26/14.
 */
public class SearchBase<E> {
    protected SearchResults<E> results;
    protected QueryBuilder queryBuilder;
    protected FullTextSession fullTextSession;
    protected String input;
    protected Query query;
    protected FullTextQuery ftq;
    protected Class<E> entity;
    protected Map<String, List<Facet>> facetMap;
    protected ArrayList<FacetingRequest> facetingRequests;
    protected Map<String, List<Facet>> appliedFacets = new HashMap<>();
    protected int MAXRESULTS = 5;
    protected String TITLE_EDGE_NGRAM_INDEX = null;
    protected String TITLE_NGRAM_INDEX = null;
    private boolean init = true;


    public SearchBase(Class<E> entity, String input){
        this.entity = entity;
        SearchManager.initIndex();
        fullTextSession = SearchManager.fullTextSession;
        results = new SearchResults<>(input);
        this.input = input;
        queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(entity).get();
    }

    protected FacetingRequest buildDiscreteFacet(String name, String field){
        return queryBuilder.facet()
                .name(name)
                .onField(field)
                .discrete()
                .includeZeroCounts(false)
                .createFacetingRequest();
    }

    public void setInput(String input){
        results.originalQuery = input;
        this.input = input;
    }

    public SearchBase<E> simpleSearch(String... fields){
        query = queryBuilder
                .keyword()
                .onFields(fields)
                .matching(input)
                .createQuery();
        return this;
    }

    public SearchBase<E> fuzzySearch(String... fields){
        query = queryBuilder
                .keyword()
                .fuzzy()
                .withThreshold(.7f)
                .onFields(fields)
                .matching(input)
                .createQuery();
        return this;
    }

    public SearchBase<E> dynamicSearch(int minResults, String... fields){
        int cresults;
        float fuzzines = 0.7f;
        FullTextQuery ftqDynamic;
        do {
            query = queryBuilder
                    .keyword()
                    .fuzzy()
                    .withThreshold(fuzzines)
                    .onFields(fields)
                    .matching(input)
                    .createQuery();
            ftqDynamic = fullTextSession.createFullTextQuery(query, entity);
            cresults = ftqDynamic.getResultSize();
            fuzzines -= 0.1f;
        }while(cresults < minResults && fuzzines > 0.2f);

        return this;
    }

    public SearchBase<E> wildcardSearch(String field){
        query = queryBuilder
                .keyword()
                .wildcard()
                .onField(field)
                .matching(input)
                .createQuery();
        return this;
    }

    public SearchBase<E> phraseSearch(String field, int sloppiness){
        query = queryBuilder
                .phrase()
                .withSlop(sloppiness)
                .onField(field)
                .sentence(input)
                .createQuery();
        return this;
    }

    public void selectFacet(String key, int position){
        if (appliedFacets.get(key) == null){
            ArrayList<Facet> val = new ArrayList<>();
            val.add(facetMap.get(key).get(position));
            appliedFacets.put(key,val);
        } else {
            appliedFacets.get(key).add(facetMap.get(key).get(position));
        }
    }

    public void resetFacets(){
        appliedFacets.forEach((k,v) -> v.clear());
    }

    protected void createFullTextQuery(){
        assert query != null;
        ftq = fullTextSession.createFullTextQuery(query, entity);
    }

    protected void applyFacets(){
        appliedFacets.forEach((k,v) -> ftq.getFacetManager().getFacetGroup(k).selectFacets(v.toArray(new Facet[v.size()])));
    }

    protected void setupFacets(){
        facetingRequests.forEach(ftq.getFacetManager()::enableFaceting);
    }

    protected void retrieveFacets(){
        facetingRequests.forEach(fr -> facetMap.put(fr.getFacetingName(),ftq.getFacetManager().getFacets(fr.getFacetingName())));
    }

    @SuppressWarnings("unchecked")
    protected void createResults(){
        results.populateResult((List <E>)ftq.list(), facetMap);
    }

    @SuppressWarnings("unchecked")
    public List<String> getSuggestions(){
        List<String> ret = new ArrayList<>();
        if (TITLE_NGRAM_INDEX != null && TITLE_EDGE_NGRAM_INDEX != null) {
            Query query = queryBuilder
                    .phrase()
                    .withSlop(10) // Has interesting effects !!
                    .onField(TITLE_NGRAM_INDEX)
                    .andField(TITLE_EDGE_NGRAM_INDEX)
                    .boostedTo(5.0f)
                    .sentence(input)
                    .createQuery();
            FullTextQuery ftq = fullTextSession.createFullTextQuery(query, entity);
            ftq.setProjection("store_title");
            ftq.setMaxResults(MAXRESULTS);
            ret.addAll(((List<Object[]>) ftq.list()).stream().map(oa -> (String) oa[0]).collect(Collectors.toList()));
        }
        else ret.add("Not Available");
        return ret;
    }


    public SearchResults<E> getResults(){
        createFullTextQuery();
        if (facetingRequests != null)
            setupFacets();
        if (facetingRequests != null)
            retrieveFacets();
        if (facetingRequests != null)
            applyFacets();
        createResults();
        return results;
    }

}
