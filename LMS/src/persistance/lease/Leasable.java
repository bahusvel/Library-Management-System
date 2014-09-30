package persistance.lease;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
public class Leasable<T extends Leasable<T>> {
    protected Double price;
    protected int leasableId;
    protected Collection<Entity<T>> entities;
    protected Collection<Return<T>> returns;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Could also try GenerationType.TABLE
    public int getLeasableId() {
        return leasableId;
    }

    public void setLeasableId(int leasableId) {
        this.leasableId = leasableId;
    }

    public void setBookReturns(Collection<Return<T>> returns) {
        this.returns = returns;
    }

    @OneToMany(mappedBy = "leasable", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Entity<T>> getEntities() {
        return entities;
    }

    public void setEntities(Collection<Entity<T>> entities) {
        this.entities = entities;
    }

}
