package persistance;

import persistance.base.AbstractItem;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class ItemEntity extends persistance.base.LeasableEntity<Item> {
    public ItemEntity(){

    }


    public ItemEntity(Item item, Date acquisitionDate) {
        super(item, acquisitionDate);
    }
}
