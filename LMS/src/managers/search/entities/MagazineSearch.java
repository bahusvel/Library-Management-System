package managers.search.entities;

import persistance.Magazine;
import managers.search.SearchBase;

/**
 * Created by denislavrov on 9/26/14.
 */
public class MagazineSearch extends SearchBase<Magazine> {
    private static final Class<Magazine> ENTITY = Magazine.class;
    public MagazineSearch(String input) {
        super(ENTITY, input);
    }

}