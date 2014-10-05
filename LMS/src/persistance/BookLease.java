package persistance;

import persistance.base.Lease;

import javax.persistence.Entity;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class BookLease extends Lease<Book> {
}
