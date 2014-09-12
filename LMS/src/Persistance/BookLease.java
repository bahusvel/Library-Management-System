package Persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "book_lease", schema = "public", catalog = "librarymanagementsystem")
public class BookLease {
    private long leaseId;
    private Date leaseDate;
    private Date dueDate;
    private boolean renewed = false;
    private BookEntity bookEntity;
    private Employee employee;
    private Member member;
    private Visit visit;

    @Id
    @Column(name = "lease_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookLease bookLease = (BookLease) o;

        if (leaseId != bookLease.leaseId) return false;
        if (renewed != bookLease.renewed) return false;
        if (dueDate != null ? !dueDate.equals(bookLease.dueDate) : bookLease.dueDate != null) return false;
        if (leaseDate != null ? !leaseDate.equals(bookLease.leaseDate) : bookLease.leaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (leaseId ^ (leaseId >>> 32));
        result = 31 * result + (leaseDate != null ? leaseDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (renewed ? 1 : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "book_entity_id", referencedColumnName = "book_entity_id", nullable = false)
    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "visitid", referencedColumnName = "visitid", nullable = false)
    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
