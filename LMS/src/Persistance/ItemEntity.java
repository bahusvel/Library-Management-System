package Persistance;

import javax.validation.constraints.NotNull;

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
    private boolean leased = false;
    private boolean available = true;
    private String location;
    private int itemEntityId;
    private Item item;
    private ItemLease itemLeases;
    private Collection<ItemReturn> itemReturns;


    @Column(name = "acquisition_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }


    @Column(name = "leased")
    @NotNull
    public boolean getLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }


    @Column(name = "available")
    @NotNull
    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


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
        if (!(o instanceof ItemEntity)) return false;

        ItemEntity that = (ItemEntity) o;

        if (available != that.available) return false;
        if (itemEntityId != that.itemEntityId) return false;
        if (leased != that.leased) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (item != null ? !item.equals(that.item) : that.item != null) return false;
        if (itemLeases != null ? !itemLeases.equals(that.itemLeases) : that.itemLeases != null) return false;
        if (itemReturns != null ? !itemReturns.equals(that.itemReturns) : that.itemReturns != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = acquisitionDate != null ? acquisitionDate.hashCode() : 0;
        result = 31 * result + (leased ? 1 : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + itemEntityId;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (itemLeases != null ? itemLeases.hashCode() : 0);
        result = 31 * result + (itemReturns != null ? itemReturns.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", nullable = false)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @OneToOne(mappedBy = "itemEntity")
    public ItemLease getItemLeases() {
        return itemLeases;
    }

    public void setItemLeases(ItemLease itemLeases) {
        this.itemLeases = itemLeases;
    }

    @OneToMany(mappedBy = "itemEntity")
    public Collection<ItemReturn> getItemReturns() {
        return itemReturns;
    }

    public void setItemReturns(Collection<ItemReturn> itemReturns) {
        this.itemReturns = itemReturns;
    }
}
