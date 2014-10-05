package persistance;

import persistance.base.Return;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */

@Entity
public class BookReturn extends Return<Book>{

    public BookReturn(){

    }

    public BookReturn(BookEntity entity, Date leaseDate, Date dueDate, Member member, Employee employee, Date returnDate) {
        this.leasableEntity = entity;
        this.leaseDate = leaseDate;
        this.dueDate = dueDate;
        this.member = member;
        this.employee = employee;
        this.returnDate = returnDate;
    }

    public BookReturn(BookLease lease, Employee employee){
        leasableEntity = lease.getLeasableEntity();
        leaseDate = lease.getLeaseDate();
        dueDate = lease.getDueDate();
        member = lease.getMember();
        this.employee = employee;
        returnDate = new Date();
    }

}
