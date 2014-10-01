package persistance.base;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 10/1/14.
 */

@javax.persistence.Entity
public abstract class Entity<T extends AbstractItem<T>> {
    protected boolean available = true;
    protected int entityId;
    protected String location;
    protected Date acquisitionDate;
    protected String suppliedBy;
    protected AbstractItem<T> abstractItem;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @NotNull
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    @ManyToOne
    public AbstractItem<T> getAbstractItem() {
        return abstractItem;
    }

    public void setAbstractItem(AbstractItem<T> leasableItem) {
        this.abstractItem = leasableItem;
    }
}
