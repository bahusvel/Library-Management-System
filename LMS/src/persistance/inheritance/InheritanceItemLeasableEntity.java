package persistance.inheritance;

import persistance.base.LeasableEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceItemLeasableEntity extends LeasableEntity<InheritanceItem> {
    public InheritanceItemLeasableEntity(){

    }

    public InheritanceItemLeasableEntity(InheritanceItem item, Date acquisitionDate) {
        abstractItem = item;
        this.acquisitionDate = acquisitionDate;
    }
}
