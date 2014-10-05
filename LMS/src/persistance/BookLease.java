package persistance;

import persistance.base.Lease;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class BookLease extends Lease<Book> {

    public BookLease(){

    }

    public BookLease(Date leaseDate, Date dueDate, Member member, Employee employee, BookEntity entity, Visit visit) {
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.leasableEntity = entity;
        this.visit = visit;
    }
}
