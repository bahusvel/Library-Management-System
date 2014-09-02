package Persistence;

import java.sql.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
public class ItemLease {
    private Date leaseDate;
    private Date dueDate;
    private Boolean renewed;
    private int itemLeaseId;
    private Employee employeeByEmployeeId;
    private ItemEntity itemEntityByItemEntityId;
    private Member memberByMemberId;

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

    public Boolean getRenewed() {
        return renewed;
    }

    public void setRenewed(Boolean renewed) {
        this.renewed = renewed;
    }

    public int getItemLeaseId() {
        return itemLeaseId;
    }

    public void setItemLeaseId(int itemLeaseId) {
        this.itemLeaseId = itemLeaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemLease itemLease = (ItemLease) o;

        if (itemLeaseId != itemLease.itemLeaseId) return false;
        if (dueDate != null ? !dueDate.equals(itemLease.dueDate) : itemLease.dueDate != null) return false;
        if (leaseDate != null ? !leaseDate.equals(itemLease.leaseDate) : itemLease.leaseDate != null) return false;
        if (renewed != null ? !renewed.equals(itemLease.renewed) : itemLease.renewed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = leaseDate != null ? leaseDate.hashCode() : 0;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (renewed != null ? renewed.hashCode() : 0);
        result = 31 * result + itemLeaseId;
        return result;
    }

    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    public ItemEntity getItemEntityByItemEntityId() {
        return itemEntityByItemEntityId;
    }

    public void setItemEntityByItemEntityId(ItemEntity itemEntityByItemEntityId) {
        this.itemEntityByItemEntityId = itemEntityByItemEntityId;
    }

    public Member getMemberByMemberId() {
        return memberByMemberId;
    }

    public void setMemberByMemberId(Member memberByMemberId) {
        this.memberByMemberId = memberByMemberId;
    }
}
