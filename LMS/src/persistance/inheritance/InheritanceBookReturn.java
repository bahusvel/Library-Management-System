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
public class InheritanceBookReturn extends Return<InheritanceBook>{

    public InheritanceBookReturn(){

    }

    public InheritanceBookReturn(InheritanceBookLeasableEntity entity, Date leaseDate, Date dueDate, Member member, Employee employee, Date returnDate) {
        this.leasableEntity = entity;
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.returnDate = returnDate;
    }

    public InheritanceBookReturn(InheritanceBookLease lease, Employee employee){
        leasableEntity = lease.getLeasableEntity();
        leaseDate = lease.getLeaseDate();
        dueDate = lease.getDueDate();
        member = lease.getMember();
        this.employee = employee;
        returnDate = new Date();
    }

}
