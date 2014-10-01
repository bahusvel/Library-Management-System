package persistance.base;

import javax.persistence.*;

/**
 * Created by denislavrov on 10/1/14.
 */
@javax.persistence.Entity
public class AbstractEntity<T extends AbstractEntity<T>>{
    protected Double price;
    protected int leasableId;

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE) // Could also try GenerationType.TABLE
    public int getLeasableId() {
        return leasableId;
    }

    public void setLeasableId(int leasableId) {
        this.leasableId = leasableId;
    }
}
