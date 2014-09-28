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
        addFacetingRequest(buildDiscreteFacet("Category", "category"));
        addFacetingRequest(buildRangeFacet("Price","price",30.0,31.0,50.0,51.0,70.0,71.0));
        addFacetingRequest(buildDiscreteFacet("Year", "releaseDate"));
        addFacetingRequest(buildRangeFacet("Pages","pages",50,51,100,101,150,151,200,201,250,251,300,301,400,401));
        addFacetingRequest(buildRangeFacet("Rating","rating",1.0,1.1,2.0,2.1,3.0,3.1,4.0,4.1));

    }
}
