package persistance.base;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Leasable<T extends Leasable<T>> {
    protected Double price;
    protected int leasableId;
    protected Collection<Entity<T>> entities;
    protected Collection<Return<T>> returns;

    @Id
    @GeneratedValue(strategy=GenerationType.TABLE) // Could also try GenerationType.TABLE
    public int getLeasableId() {
        return leasableId;
    }

    public void setLeasableId(int leasableId) {
        this.leasableId = leasableId;
    }

    @OneToMany(mappedBy = "leasable", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Return<T>> getReturns(){
        return returns;
    }

    public void setReturns(Collection<Return<T>> returns) {
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
