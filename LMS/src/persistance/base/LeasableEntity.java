package persistance.base;

import lombok.*;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
@Data
@javax.persistence.Entity
@EqualsAndHashCode(callSuper = true, exclude = {"lease", "returns"})
@ToString(exclude = {"lease", "returns"})
public class LeasableEntity<T extends AbstractItem<T>> extends Entity<T>{
    @NotNull
    protected boolean leased = false;
    @OneToOne(mappedBy = "leasableEntity")
    protected Lease<T> lease;
    @OneToMany(mappedBy = "leasableEntity")
    protected Collection<Return<T>> returns;

    public LeasableEntity(){

    }


    public LeasableEntity(AbstractItem<T> book, Date acquisitionDate) {
        abstractItem = book;
        this.acquisitionDate = acquisitionDate;
    }


}
