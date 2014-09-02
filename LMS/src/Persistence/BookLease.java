package Persistence;

import java.sql.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
public class BookLease {
    private long leaseId;
    private Date leaseDate;
    private Date dueDate;
    private boolean renewed;
    private BookEntity bookEntityByBookEntityId;
    private Employee employeeByEmployeeId;
    private Member memberByMemberId;

    public long getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(long leaseId) {
        this.leaseId = leaseId;
    }

    public Date getLeaseDate() {
        return leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

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

    public BookEntity getBookEntityByBookEntityId() {
        return bookEntityByBookEntityId;
    }

    public void setBookEntityByBookEntityId(BookEntity bookEntityByBookEntityId) {
        this.bookEntityByBookEntityId = bookEntityByBookEntityId;
    }

    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    public Member getMemberByMemberId() {
        return memberByMemberId;
    }

    public void setMemberByMemberId(Member memberByMemberId) {
        this.memberByMemberId = memberByMemberId;
    }
}
