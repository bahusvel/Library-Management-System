package persistance.lease;

import persistance.Employee;
import persistance.Member;
import persistance.Visit;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
public class Lease<T extends Leasable<T>> {
    protected long leaseId;
    protected Date leaseDate;
    protected Date dueDate;
    protected boolean renewed = false;
    protected Entity<T> entity;
    protected Employee employee;
    protected Member member;
    protected Visit visit;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(long leaseId) {
        this.leaseId = leaseId;
    }

    @Column(name = "lease_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Column(name = "renewed")
    @NotNull
    public boolean isRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }


    @OneToOne
    public Entity<T> getEntity() {
        return entity;
    }

    public void setEntity(Entity<T> entity) {
        this.entity = entity;
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

