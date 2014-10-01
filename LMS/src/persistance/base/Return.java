package persistance.base;

import persistance.Employee;
import persistance.Member;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 9/30/14.
 */
@javax.persistence.Entity
public class Return<T extends AbstractEntity<T>> {
    protected Date leaseDate;
    protected Date dueDate;
    protected double ammountCharged = 0.0;
    protected Date returnDate;
    protected String memberComments;
    protected Double memberRating;
    protected int returnId;
    protected Entity<T> entity;
    protected Employee employee;
    protected Member member;
    protected boolean lost = false;
    protected Leasable<T> leasable;


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
    public double getAmmountCharged() {
        return ammountCharged;
    }

    public void setAmmountCharged(double ammountCharged) {
        this.ammountCharged = ammountCharged;
    }


    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getMemberComments() {
        return memberComments;
    }

    public void setMemberComments(String memberComments) {
        this.memberComments = memberComments;
    }


    public Double getMemberRating() {
        return memberRating;
    }

    public void setMemberRating(Double memberRating) {
        this.memberRating = memberRating;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getReturnId() {
        return returnId;
    }

    public void setReturnId(int returnId) {
        this.returnId = returnId;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }


    @ManyToOne
    public Leasable<T> getLeasable() {
        return leasable;
    }

    public void setLeasable(Leasable<T> leasable) {
        this.leasable = leasable;
    }

    @ManyToOne
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
}
