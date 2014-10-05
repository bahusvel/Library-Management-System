package persistance.base;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by denislavrov on 10/1/14.
 */
@Entity
public class Request<T extends AbstractItem<T>> {
    protected int requestid;
    protected String supplier;
    protected String uri;
    protected int quantity = 1;
    protected AbstractItem<T> abstractItem;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    @Basic
    @NotNull
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne
    public AbstractItem<T> getAbstractItem() {
        return abstractItem;
    }

    public void setAbstractItem(AbstractItem<T> abstractItem) {
        this.abstractItem = abstractItem;
    }
}
