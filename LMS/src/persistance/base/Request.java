package persistance.base;

import lombok.*;
import persistance.Member;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by denislavrov on 10/1/14.
 */
@Data
@Entity
@EqualsAndHashCode(exclude = "abstractItem")
public class Request<T extends AbstractItem<T>> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected int requestid;
    @NotNull
    protected String supplier;
    protected String uri;
    @NotNull
    protected int quantity = 1;
    @ManyToOne
    protected AbstractItem<T> abstractItem;
}
