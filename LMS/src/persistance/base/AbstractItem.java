package persistance.base;

import javax.persistence.*;

/**
 * Created by denislavrov on 10/1/14.
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractItem<T extends AbstractItem<T>>{
    protected Double price;
    protected int id;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int leasableId) {
        this.id = leasableId;
    }
}
