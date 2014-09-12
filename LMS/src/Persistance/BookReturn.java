package Persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "book_return", schema = "public", catalog = "librarymanagementsystem")
public class BookReturn {
    private Date leaseDate;
    private Date dueDate;
    private double ammountCharged = 0.0;
    private Date returnDate;
    private String memberComments;
    private Double memberRating;
    private int bookReturnId;
    private BookEntity bookEntity;
    private Employee employee;
    private Member member;


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


    @Column(name = "ammount_charged")
    @NotNull
    public double getAmmountCharged() {
        return ammountCharged;
    }

    public void setAmmountCharged(double ammountCharged) {
        this.ammountCharged = ammountCharged;
    }


    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    @Column(name = "member_comments")
    public String getMemberComments() {
        return memberComments;
    }

    public void setMemberComments(String memberComments) {
        this.memberComments = memberComments;
    }


    @Column(name = "member_rating")
    public Double getMemberRating() {
        return memberRating;
    }

    public void setMemberRating(Double memberRating) {
        this.memberRating = memberRating;
    }

    @Id
    @Column(name = "book_return_id")
    @NotNull
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getBookReturnId() {
        return bookReturnId;
    }

    public void setBookReturnId(int bookReturnId) {
        this.bookReturnId = bookReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookReturn)) return false;

        BookReturn that = (BookReturn) o;

        if (Double.compare(that.ammountCharged, ammountCharged) != 0) return false;
        if (bookReturnId != that.bookReturnId) return false;
        if (bookEntity != null ? !bookEntity.equals(that.bookEntity) : that.bookEntity != null) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (leaseDate != null ? !leaseDate.equals(that.leaseDate) : that.leaseDate != null) return false;
        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (memberComments != null ? !memberComments.equals(that.memberComments) : that.memberComments != null)
            return false;
        if (memberRating != null ? !memberRating.equals(that.memberRating) : that.memberRating != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        temp = Double.doubleToLongBits(ammountCharged);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (memberComments != null ? memberComments.hashCode() : 0);
        result = 31 * result + (memberRating != null ? memberRating.hashCode() : 0);
        result = 31 * result + bookReturnId;
        result = 31 * result + (bookEntity != null ? bookEntity.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (member != null ? member.hashCode() : 0);
        return result;
    }

    @ManyToOne
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
}
