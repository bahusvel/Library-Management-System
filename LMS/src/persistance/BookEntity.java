package persistance;


import persistance.base.LeasableEntity;

import javax.persistence.Entity;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class BookEntity extends LeasableEntity<Book> {
}
