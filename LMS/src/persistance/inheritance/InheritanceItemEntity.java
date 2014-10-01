package persistance.inheritance;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceItemEntity extends persistance.base.Entity<InheritanceItem> {
    public InheritanceItemEntity(){

    }

    public InheritanceItemEntity(InheritanceItem item, Date acquisitionDate) {
        leasable = item;
        this.acquisitionDate = acquisitionDate;
    }
}
