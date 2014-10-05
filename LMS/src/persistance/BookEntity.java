package persistance;


import persistance.base.LeasableEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class BookEntity extends LeasableEntity<Book> {

    public BookEntity(){

    }


    public BookEntity(Book book, Date acquisitionDate) {
        abstractItem = book;
        this.acquisitionDate = acquisitionDate;
    }

}
