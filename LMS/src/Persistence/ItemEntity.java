package Persistence;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
public class ItemEntity {
    private Date acquisitionDate;
    private Boolean leased;
    private Boolean available;
    private String location;
    private int itemEntityId;
    private Item itemByItemId;
    private Collection<ItemLease> itemLeasesByItemEntityId;
    private Collection<ItemReturn> itemReturnsByItemEntityId;

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Boolean getLeased() {
        return leased;
    }

    public void setLeased(Boolean leased) {
        this.leased = leased;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getItemEntityId() {
        return itemEntityId;
    }

    public void setItemEntityId(int itemEntityId) {
        this.itemEntityId = itemEntityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity that = (ItemEntity) o;

        if (itemEntityId != that.itemEntityId) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (available != null ? !available.equals(that.available) : that.available != null) return false;
        if (leased != null ? !leased.equals(that.leased) : that.leased != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = acquisitionDate != null ? acquisitionDate.hashCode() : 0;
        result = 31 * result + (leased != null ? leased.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + itemEntityId;
        return result;
    }

    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    public Collection<ItemLease> getItemLeasesByItemEntityId() {
        return itemLeasesByItemEntityId;
    }

    public void setItemLeasesByItemEntityId(Collection<ItemLease> itemLeasesByItemEntityId) {
        this.itemLeasesByItemEntityId = itemLeasesByItemEntityId;
    }

    public Collection<ItemReturn> getItemReturnsByItemEntityId() {
        return itemReturnsByItemEntityId;
    }

    public void setItemReturnsByItemEntityId(Collection<ItemReturn> itemReturnsByItemEntityId) {
        this.itemReturnsByItemEntityId = itemReturnsByItemEntityId;
    }
}
