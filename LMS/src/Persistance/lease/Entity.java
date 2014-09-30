package persistance.lease;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by denislavrov on 9/30/14.
 */
public class Entity<T extends Leasable<T>> {
    protected boolean available = true;
    protected boolean leased = false;
    protected T leasable;
    protected Lease<T> lease;
    protected Collection<Return<T>> returns;
    protected int entityId;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @NotNull
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @NotNull
    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }


    @ManyToOne
    public T getBook() {
        return leasable;
    }

    public void setBook(T leasable) {
        this.leasable = leasable;
    }

    @OneToOne(mappedBy = "entity")
    public Lease<T> getLease() {
        return lease;
    }

    public void setLease(Lease<T> lease) {
        this.lease = lease;
    }

    @OneToMany(mappedBy = "entity")
    public Collection<Return<T>> getReturns() {
        return returns;
    }

    public void setReturns(Collection<Return<T>> returns) {
        this.returns = returns;
    }

}
