package Persistance;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "item_return", schema = "public", catalog = "librarymanagementsystem")
public class ItemReturn {
    private Date leaseDate;
    private Date dueDate;
    private Date returnDate;
    private Double ammountCharged;
    private int itemReturnId;
    private Employee employeeByEmployeeId;
    private ItemEntity itemEntityByItemEntityId;
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
    @Column(name = "return_date")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "ammount_charged")
    public Double getAmmountCharged() {
        return ammountCharged;
    }

    public void setAmmountCharged(Double ammountCharged) {
        this.ammountCharged = ammountCharged;
    }

    @Id
    @Column(name = "item_return_id")
    public int getItemReturnId() {
        return itemReturnId;
    }

    public void setItemReturnId(int itemReturnId) {
        this.itemReturnId = itemReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemReturn that = (ItemReturn) o;

        if (itemReturnId != that.itemReturnId) return false;
        if (ammountCharged != null ? !ammountCharged.equals(that.ammountCharged) : that.ammountCharged != null)
            return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (leaseDate != null ? !leaseDate.equals(that.leaseDate) : that.leaseDate != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        result = 31 * result + (ammountCharged != null ? ammountCharged.hashCode() : 0);
        result = 31 * result + itemReturnId;
        return result;
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
    @JoinColumn(name = "item_entity_id", referencedColumnName = "item_entity_id")
    public ItemEntity getItemEntityByItemEntityId() {
        return itemEntityByItemEntityId;
    }

    public void setItemEntityByItemEntityId(ItemEntity itemEntityByItemEntityId) {
        this.itemEntityByItemEntityId = itemEntityByItemEntityId;
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