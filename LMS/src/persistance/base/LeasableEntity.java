package persistance.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
public class LeasableEntity<T extends AbstractItem<T>> {
    protected boolean available = true;
    protected boolean leased = false;
    protected LeasableItem<T> leasableItem;
    protected Lease<T> lease;
    protected Collection<Return<T>> returns;
    protected int entityId;
    protected String location;
    protected Date acquisitionDate;
    protected String suppliedBy;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }


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
    public LeasableItem<T> getLeasableItem() {
        return leasableItem;
    }

    public void setLeasableItem(LeasableItem<T> leasableItem) {
        this.leasableItem = leasableItem;
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
