package persistance.base;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
public abstract class LeasableItem<T extends AbstractItem<T>> extends AbstractItem<T> {
    protected Collection<LeasableEntity<T>> entities;
    protected Collection<Return<T>> returns;

    @OneToMany(mappedBy = "leasableItem", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<Return<T>> getReturns(){
        return returns;
    }

    public void setReturns(Collection<Return<T>> returns) {
        this.returns = returns;
    }

    @OneToMany(mappedBy = "leasableItem", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<LeasableEntity<T>> getEntities() {
        return entities;
    }

    public void setEntities(Collection<LeasableEntity<T>> entities) {
        this.entities = entities;
    }

}
