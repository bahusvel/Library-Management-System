package persistance.base;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
@Data
@javax.persistence.Entity
@EqualsAndHashCode(callSuper = true, exclude = {"entities", "returns"})
public abstract class LeasableItem<T extends AbstractItem<T>> extends AbstractItem<T> {
    @OneToMany(mappedBy = "abstractItem", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Collection<Entity<T>> entities;
    @OneToMany(mappedBy = "leasableItem", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Collection<Return<T>> returns;
}
