package persistance.base;

import lombok.*;
import persistance.Employee;
import persistance.Member;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
@Data
@javax.persistence.Entity
@EqualsAndHashCode(exclude = {"leasableEntity", "employee", "member", "leasableItem"})
public class Return<T extends AbstractItem<T>> {
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date leaseDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date dueDate;
    @NotNull
    protected double ammountCharged = 0.0;
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date returnDate;
    protected String memberComments;
    protected Double memberRating;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected int returnId;
    @ManyToOne
    protected LeasableEntity<T> leasableEntity;
    @ManyToOne
    protected Employee employee;
    @ManyToOne
    protected Member member;
    protected boolean lost = false;
    @ManyToOne
    protected LeasableItem<T> leasableItem;

    public Return(){

    }

    public Return(Lease<T> lease, Employee employee){
        leasableEntity = lease.getLeasableEntity();
        leaseDate = lease.getLeaseDate();
        dueDate = lease.getDueDate();
        member = lease.getMember();
        this.employee = employee;
        returnDate = new Date();
    }


}
