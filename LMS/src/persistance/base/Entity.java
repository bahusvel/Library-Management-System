package persistance.base;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 10/1/14.
 */

@Data
@javax.persistence.Entity
@EqualsAndHashCode(exclude = "abstractItem")
public abstract class Entity<T extends AbstractItem<T>> {
    @NotNull
    protected boolean available = true;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected int entityId;
    protected String location;
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date acquisitionDate;
    protected String suppliedBy;
    @ManyToOne
    protected AbstractItem<T> abstractItem;
}
