package persistance.base;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang.exception.ExceptionUtils;
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
@EqualsAndHashCode(exclude = "requests")
public abstract class AbstractItem<T extends AbstractItem<T>>{
    @Field(analyze = Analyze.NO)
    @NumericField // LUCENE NEEDS THIS HERE, OTHERWISE WONT RETURN RESULTS ON FACETING
    protected Double price;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected int id;
    @OneToMany(mappedBy = "abstractItem")
    protected Collection<Request<T>> requests;

    public Double getPrice() {
        return price;
    }
}
