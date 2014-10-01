package persistance.inheritance;


import persistance.base.LeasableEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceBookLeasableEntity extends LeasableEntity<InheritanceBook> {

    public InheritanceBookLeasableEntity(){

    }


    public InheritanceBookLeasableEntity(InheritanceBook book, Date acquisitionDate) {
        leasableItem = book;
        this.acquisitionDate = acquisitionDate;
    }

}
