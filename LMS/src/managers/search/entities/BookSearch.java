package managers.search.entities;


import managers.search.SearchBase;
import persistance.Book;

/**
 * Created by denislavrov on 9/26/14.
 */
public class BookSearch extends SearchBase<Book> {
    public BookSearch(String input) {
        super(Book.class, input);
        initSuggestions("edgeTitle", "ngramTitle");
        initFaceting();
        addFacetingRequest(buildDiscreteFacet("categoryFacetingRequest", "category"));
    }
}
