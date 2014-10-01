package persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "item_lease", schema = "public", catalog = "inheritance")
public class ItemLease {
    private Date leaseDate;
    private Date dueDate;
    private boolean renewed = false;
    private int itemLeaseId;
    private Employee employee;
    private ItemEntity itemEntity;
    private Member member;
    private Visit visit;

    public ItemLease(Date leaseDate, Date dueDate, Member member, Employee employee, ItemEntity itemEntity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.itemEntity = itemEntity;
        this.visit = visit;
    }

    public ItemLease() {
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
    public boolean getRenewed() {
        return renewed;
    }

    public void setRenewed(boolean renewed) {
        this.renewed = renewed;
    }

    @Id
    @Column(name = "item_lease_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getItemLeaseId() {
        return itemLeaseId;
    }

    public void setItemLeaseId(int itemLeaseId) {
        this.itemLeaseId = itemLeaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemLease)) return false;

        ItemLease itemLease = (ItemLease) o;

        if (itemLeaseId != itemLease.itemLeaseId) return false;
        if (renewed != itemLease.renewed) return false;
        if (dueDate != null ? !dueDate.equals(itemLease.dueDate) : itemLease.dueDate != null) return false;
        if (leaseDate != null ? !leaseDate.equals(itemLease.leaseDate) : itemLease.leaseDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (renewed ? 1 : 0);
        result = 31 * result + itemLeaseId;
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

    @OneToOne
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

    @ManyToOne
    @JoinColumn(name = "visitid", referencedColumnName = "visitid", nullable = false)
    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
