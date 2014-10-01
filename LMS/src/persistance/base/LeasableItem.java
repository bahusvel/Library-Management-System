package persistance.base;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
public abstract class LeasableItem<T extends AbstractItem<T>> extends AbstractItem<T> {
    protected Collection<Entity<T>> entities;
    protected Collection<Return<T>> returns;

    @OneToMany(mappedBy = "leasableItem", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Return<T>> getReturns(){
        return returns;
    }

    public void setReturns(Collection<Return<T>> returns) {
        this.returns = returns;
    }

    @OneToMany(mappedBy = "abstractItem", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Entity<T>> getEntities() {
        return entities;
    }

    public void setEntities(Collection<Entity<T>> entities) {
        this.entities = entities;
    }

}
