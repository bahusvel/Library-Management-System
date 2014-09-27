package managers.search;

import org.hibernate.search.query.facet.Facet;

import java.util.List;
import java.util.Map;

/**
 * Created by denislavrov on 9/25/14.
 */
public class SearchResults<E> {
    protected List<E> resultList;
    protected int resultCount;
    protected long searchDuration;
    protected String originalQuery;
    protected String suggestedQuery;
    protected long stime;
    protected Map<String, List<Facet>> facetMap;

    public SearchResults(String originalQuery){
        this.originalQuery = originalQuery;
        stime = System.nanoTime()/1000;
    }

    public void populateResult(List<E> resultList, String suggestedQuery) {
        this.resultList = resultList;
        this.suggestedQuery = suggestedQuery;
        resultCount = resultList.size();
        searchDuration = System.nanoTime()/1000L - stime;
    }

    public void populateResult(List<E> resultList) {
        this.resultList = resultList;
        resultCount = resultList.size();
        searchDuration = System.nanoTime()/1000L - stime;
    }

    public void populateResult(List<E> resultList, Map<String, List<Facet>> facetMap) {
        this.resultList = resultList;
        resultCount = resultList.size();
        searchDuration = System.nanoTime()/1000L - stime;
        this.facetMap = facetMap;
    }



    public void resetClock(){
        stime = System.nanoTime()/1000;
    }

    public List<E> getResultList() {
        return resultList;
    }

    public int getResultCount() {
        return resultCount;
    }

    public long getSearchDuration() {
        return searchDuration;
    }

    public String getOriginalQuery() {
        return originalQuery;
    }

    public String getSuggestedQuery() {
        return suggestedQuery;
    }

    public Map<String, List<Facet>> getFacetMap() {
        return facetMap;
    }
}
