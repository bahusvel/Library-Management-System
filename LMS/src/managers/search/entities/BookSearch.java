package managers.search.entities;


import managers.search.SearchBase;
import persistance.Book;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denislavrov on 9/26/14.
 */
public class BookSearch extends SearchBase<Book> {

    public BookSearch(String input) {
        super(Book.class, input);
        TITLE_EDGE_NGRAM_INDEX = "edgeTitle";
        TITLE_NGRAM_INDEX = "ngramTitle";
        facetingRequests =  new ArrayList<>();
        facetMap = new HashMap<>();
        buildCategoryFacet();
    }

    public void buildCategoryFacet(){
        facetingRequests.add(buildDiscreteFacet("categoryFacetingRequest","category"));
    }
}
