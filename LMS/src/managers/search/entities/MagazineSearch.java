package managers.search.entities;

import managers.search.SearchBase;
import persistance.Magazine;

/**
 * Created by denislavrov on 9/26/14.
 */
public class MagazineSearch extends SearchBase<Magazine> {
    private static final Class<Magazine> ENTITY = Magazine.class;
    public MagazineSearch(String input) {
        super(ENTITY, input);
        initFaceting();
        addFacetingRequest(buildDiscreteFacet("Language", "language"));
        addFacetingRequest(buildRangeFacet("Price","price",30.0,31.0,50.0,51.0,70.0,71.0));
    }

}