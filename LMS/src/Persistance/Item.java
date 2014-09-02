package Persistance;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class Item {
    private String name;
    private String description;
    private String condition;
    private String category;
    private int itemId;
    private Double price;
    private Collection<ItemEntity> itemEntitiesByItemId;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "condition")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != item.itemId) return false;
        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        if (condition != null ? !condition.equals(item.condition) : item.condition != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + itemId;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "itemByItemId")
    public Collection<ItemEntity> getItemEntitiesByItemId() {
        return itemEntitiesByItemId;
    }

    public void setItemEntitiesByItemId(Collection<ItemEntity> itemEntitiesByItemId) {
        this.itemEntitiesByItemId = itemEntitiesByItemId;
    }
}
