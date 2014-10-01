package persistance.inheritance;

import persistance.Employee;
import persistance.Member;
import persistance.Visit;
import persistance.base.Lease;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceItemLease extends Lease<InheritanceItem> {

    public InheritanceItemLease(Date leaseDate, Date dueDate, Member member, Employee employee, InheritanceItemLeasableEntity itemEntity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        leasableEntity = itemEntity;
        this.visit = visit;
    }

    public InheritanceItemLease() {
    }
}
