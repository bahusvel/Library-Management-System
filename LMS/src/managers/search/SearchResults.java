package managers.search;

import java.util.List;

/**
 * Created by denislavrov on 9/25/14.
 */
public class SearchResults<E> {
    private List<E> resultList;
    private int resultCount;
    private long searchDuration;
    private String originalQuery;
    private String suggestedQuery;
    private long stime;

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
}
