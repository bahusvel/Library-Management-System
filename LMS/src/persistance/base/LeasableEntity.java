package persistance.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
public class LeasableEntity<T extends AbstractItem<T>> extends Entity<T>{
    protected boolean leased = false;
    protected Lease<T> lease;
    protected Collection<Return<T>> returns;

    @NotNull
    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }


    @OneToOne(mappedBy = "leasableEntity")
    public Lease<T> getLease() {
        return lease;
    }

    public void setLease(Lease<T> lease) {
        this.lease = lease;
    }

    @OneToMany(mappedBy = "leasableEntity")
    public Collection<Return<T>> getReturns() {
        return returns;
    }

    public void setReturns(Collection<Return<T>> returns) {
        this.returns = returns;
    }

}