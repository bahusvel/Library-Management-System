package persistance.base;

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
public class Lease<T extends AbstractItem<T>> {
    protected long leaseId;
    protected Date leaseDate;
    protected Date dueDate;
    protected boolean renewed = false;
    protected LeasableEntity<T> leasableEntity;
    protected Employee employee;
    protected Member member;
    protected Visit visit;

    public Lease(Date leaseDate, Date dueDate, Member member, Employee employee, LeasableEntity<T> entity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.leasableEntity = entity;
        this.visit = visit;
    }

    public Lease() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(long leaseId) {
        this.leaseId = leaseId;
    }

    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @NotNull
    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }


    @OneToOne
    public LeasableEntity<T> getLeasableEntity() {
        return leasableEntity;
    }

    public void setLeasableEntity(LeasableEntity<T> leasableEntity) {
        this.leasableEntity = leasableEntity;
    }

    @ManyToOne
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

}

