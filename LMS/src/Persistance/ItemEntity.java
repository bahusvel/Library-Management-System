package Persistance;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "item_entity", schema = "public", catalog = "librarymanagementsystem")
public class ItemEntity {
    private Date acquisitionDate;
    private Boolean leased;
    private Boolean available;
    private String location;
    private int itemEntityId;
    private Item itemByItemId;
    private ItemLease itemLeasesByItemEntityId;
    private Collection<ItemReturn> itemReturnsByItemEntityId;

    @Basic
    @Column(name = "acquisition_date")
    @Temporal(TemporalType.DATE)
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Basic
    @Column(name = "leased")
    public Boolean getLeased() {
        return leased;
    }

    public void setLeased(Boolean leased) {
        this.leased = leased;
    }

    @Basic
    @Column(name = "available")
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Id
    @Column(name = "item_entity_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id")
    public Item getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(Item itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    @OneToOne(mappedBy = "itemEntityByItemEntityId")
    public ItemLease getItemLeasesByItemEntityId() {
        return itemLeasesByItemEntityId;
    }

    public void setItemLeasesByItemEntityId(ItemLease itemLeasesByItemEntityId) {
        this.itemLeasesByItemEntityId = itemLeasesByItemEntityId;
    }

    @OneToMany(mappedBy = "itemEntityByItemEntityId")
    public Collection<ItemReturn> getItemReturnsByItemEntityId() {
        return itemReturnsByItemEntityId;
    }

    public void setItemReturnsByItemEntityId(Collection<ItemReturn> itemReturnsByItemEntityId) {
        this.itemReturnsByItemEntityId = itemReturnsByItemEntityId;
    }
}
