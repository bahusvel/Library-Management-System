package persistance.base;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.NumericField;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 10/1/14.
 */
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractItem<T extends AbstractItem<T>>{
    protected Double price;
    protected int id;
    protected Collection<Request<T>> requests;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int leasableId) {
        this.id = leasableId;
    }


    @Field(analyze = Analyze.NO)
    @NumericField // LUCENE NEEDS THIS HERE, OTHERWISE WONT RETURN RESULTS ON FACETING
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @OneToMany(mappedBy = "abstractItem")
    public Collection<Request<T>> getRequests() {
        return requests;
    }

    public void setRequests(Collection<Request<T>> requests) {
        this.requests = requests;
    }
}
