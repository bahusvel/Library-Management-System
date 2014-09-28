package managers.search.entities;

import persistance.Item;
import managers.search.SearchBase;

/**
 * Created by denislavrov on 9/26/14.
 */
public class ItemSearch extends SearchBase<Item> {
    private static final Class<Item> ENTITY = Item.class;
    public ItemSearch(String input) {
        super(ENTITY, input);
        initFaceting();
        addFacetingRequest(buildDiscreteFacet("Category", "category"));
        addFacetingRequest(buildDiscreteFacet("Condition", "condition"));
        addFacetingRequest(buildRangeFacet("Price","price",30.0,31.0,50.0,51.0,70.0,71.0));
    }
}
