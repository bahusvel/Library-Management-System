package Persistance;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "book_return", schema = "public", catalog = "librarymanagementsystem")
public class BookReturn {
    private Date leaseDate;
    private Date dueDate;
    private Double ammountCharged;
    private Date returnDate;
    private String memberComments;
    private Double memberRating;
    private int bookReturnId;
    private BookEntity bookEntityByBookEntityId;
    private Employee employeeByEmployeeId;
    private Member memberByMemberId;

    @Basic
    @Column(name = "lease_date")
    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    @Basic
    @Column(name = "due_date")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "ammount_charged")
    public Double getAmmountCharged() {
        return ammountCharged;
    }

    public void setAmmountCharged(Double ammountCharged) {
        this.ammountCharged = ammountCharged;
    }

    @Basic
    @Column(name = "return_date")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "member_comments")
    public String getMemberComments() {
        return memberComments;
    }

    public void setMemberComments(String memberComments) {
        this.memberComments = memberComments;
    }

    @Basic
    @Column(name = "member_rating")
    public Double getMemberRating() {
        return memberRating;
    }

    public void setMemberRating(Double memberRating) {
        this.memberRating = memberRating;
    }

    @Id
    @Column(name = "book_return_id")
    public int getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(int bookReturnId) {
        this.bookReturnId = bookReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookReturn that = (BookReturn) o;

        if (bookReturnId != that.bookReturnId) return false;
        if (ammountCharged != null ? !ammountCharged.equals(that.ammountCharged) : that.ammountCharged != null)
            return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (leaseDate != null ? !leaseDate.equals(that.leaseDate) : that.leaseDate != null) return false;
        if (memberComments != null ? !memberComments.equals(that.memberComments) : that.memberComments != null)
            return false;
        if (memberRating != null ? !memberRating.equals(that.memberRating) : that.memberRating != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (ammountCharged != null ? ammountCharged.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (memberComments != null ? memberComments.hashCode() : 0);
        result = 31 * result + (memberRating != null ? memberRating.hashCode() : 0);
        result = 31 * result + bookReturnId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_entity_id", referencedColumnName = "book_entity_id")
    public BookEntity getBookEntityByBookEntityId() {
        return bookEntityByBookEntityId;
    }

    public void setBookEntityByBookEntityId(BookEntity bookEntityByBookEntityId) {
        this.bookEntityByBookEntityId = bookEntityByBookEntityId;
    }

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id")
    public Member getMemberByMemberId() {
        return memberByMemberId;
    }

    public void setMemberByMemberId(Member memberByMemberId) {
        this.memberByMemberId = memberByMemberId;
    }
}
