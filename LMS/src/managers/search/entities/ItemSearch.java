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
    }
}
