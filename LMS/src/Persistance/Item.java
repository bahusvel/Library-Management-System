package Persistance;

import com.sun.istack.internal.NotNull;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
public class Item {
    private String name;
    private String description;
    private String condition;
    private String category;
    private int itemId;
    private double price;
    private Collection<ItemEntity> itemEntities;


    @Column(name = "name")
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "description")
    @Field
    @Analyzer(definition = "TokenizingLower")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "condition")
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


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


    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (itemId != item.itemId) return false;
        if (Double.compare(item.price, price) != 0) return false;
        if (category != null ? !category.equals(item.category) : item.category != null) return false;
        if (condition != null ? !condition.equals(item.condition) : item.condition != null) return false;
        if (description != null ? !description.equals(item.description) : item.description != null) return false;
        if (itemEntities != null ? !itemEntities.equals(item.itemEntities) : item.itemEntities != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + itemId;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (itemEntities != null ? itemEntities.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "item")
    public Collection<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    public void setItemEntities(Collection<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }
}
