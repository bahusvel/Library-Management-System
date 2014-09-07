package Persistance;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "item_return", schema = "public", catalog = "librarymanagementsystem")
public class ItemReturn {
    private Date leaseDate;
    private Date dueDate;
    private Date returnDate;
    private double ammountCharged;
    private int itemReturnId;
    private Employee employee;
    private ItemEntity itemEntity;
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


    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    @Column(name = "ammount_charged")
    @NotNull
    public double getAmmountCharged() {
        return ammountCharged;
    }

    public void setAmmountCharged(double ammountCharged) {
        this.ammountCharged = ammountCharged;
    }

    @Id
    @Column(name = "item_return_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @NotNull
    public int getItemReturnId() {
        return itemReturnId;
    }

    public void setItemReturnId(int itemReturnId) {
        this.itemReturnId = itemReturnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemReturn)) return false;

        ItemReturn that = (ItemReturn) o;

        if (Double.compare(that.ammountCharged, ammountCharged) != 0) return false;
        if (itemReturnId != that.itemReturnId) return false;
        if (dueDate != null ? !dueDate.equals(that.dueDate) : that.dueDate != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (itemEntity != null ? !itemEntity.equals(that.itemEntity) : that.itemEntity != null) return false;
        if (leaseDate != null ? !leaseDate.equals(that.leaseDate) : that.leaseDate != null) return false;
        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        temp = Double.doubleToLongBits(ammountCharged);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + itemReturnId;
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (itemEntity != null ? itemEntity.hashCode() : 0);
        result = 31 * result + (member != null ? member.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "item_entity_id", referencedColumnName = "item_entity_id", nullable = false)
    public ItemEntity getItemEntity() {
        return itemEntity;
    }

    public void setItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
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
