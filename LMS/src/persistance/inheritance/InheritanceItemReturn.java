package persistance.inheritance;

import persistance.Employee;
import persistance.Member;
import persistance.base.Return;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceItemReturn extends Return<InheritanceItem> {


    public InheritanceItemReturn(){

    }

    public InheritanceItemReturn(InheritanceItemLeasableEntity itemEntity, Date leaseDate, Date dueDate, Member member, Employee employee, Date returnDate) {
        leasableEntity = itemEntity;
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.returnDate = returnDate;
    }

    public InheritanceItemReturn(InheritanceItemLease itemLease, Employee employee){
        leasableEntity = itemLease.getLeasableEntity();
        leaseDate = itemLease.getLeaseDate();
        dueDate = itemLease.getDueDate();
        member = itemLease.getMember();
        this.employee = employee;
        returnDate = new Date();
    }
}
