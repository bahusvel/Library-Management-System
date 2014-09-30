package persistance.inheritance;

import persistance.Employee;
import persistance.Member;
import persistance.Visit;
import persistance.lease.Lease;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceBookLease extends Lease<InheritanceBook> {

    public InheritanceBookLease(){

    }

    public InheritanceBookLease(Date leaseDate, Date dueDate, Member member, Employee employee, InheritanceBookEntity entity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.entity = entity;
        this.visit = visit;
    }
}
