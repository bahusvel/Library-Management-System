package persistance.inheritance;


import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceBookEntity extends persistance.base.Entity<InheritanceBook> {

    public InheritanceBookEntity(){

    }


    public InheritanceBookEntity(InheritanceBook book, Date acquisitionDate) {
        leasable = book;
        this.acquisitionDate = acquisitionDate;
    }

}
