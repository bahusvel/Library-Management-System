package persistance;

import persistance.base.Lease;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class ItemLease extends Lease<Item> {

    public ItemLease(Date leaseDate, Date dueDate, Member member, Employee employee, ItemEntity itemEntity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        leasableEntity = itemEntity;
        this.visit = visit;
    }

    public ItemLease() {
    }
}
