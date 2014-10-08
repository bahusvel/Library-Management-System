package persistance.base;

import lombok.*;
import persistance.Employee;
import persistance.Member;
import persistance.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
@Data
@EqualsAndHashCode(exclude = {"leasableEntity", "employee", "member", "visit"})
public class Lease<T extends AbstractItem<T>> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    protected long leaseId;
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date leaseDate;
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date dueDate;
    @NotNull
    protected boolean renewed = false;
    @OneToOne
    protected LeasableEntity<T> leasableEntity;
    @ManyToOne
    protected Employee employee;
    @ManyToOne
    protected Member member;
    @ManyToOne
    protected Visit visit;

    public Lease(Date leaseDate, Date dueDate, Member member, Employee employee, LeasableEntity<T> entity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        leasableEntity = entity;
        this.visit = visit;
    }

    public Lease() {
    }
}

