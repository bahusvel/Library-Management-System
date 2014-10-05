package persistance;

import persistance.base.Return;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class ItemReturn extends Return<Item> {


    public ItemReturn(){

    }

    public ItemReturn(ItemEntity itemEntity, Date leaseDate, Date dueDate, Member member, Employee employee, Date returnDate) {
        leasableEntity = itemEntity;
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.returnDate = returnDate;
    }

    public ItemReturn(ItemLease itemLease, Employee employee){
        leasableEntity = itemLease.getLeasableEntity();
        leaseDate = itemLease.getLeaseDate();
        dueDate = itemLease.getDueDate();
        member = itemLease.getMember();
        this.employee = employee;
        returnDate = new Date();
    }
}
