package Persistance;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "book_entity", schema = "public", catalog = "librarymanagementsystem")
public class BookEntity {
    private boolean available;
    private String location;
    private int bookEntityId;
    private boolean leased;
    private Date acquisitionDate;
    private Book bookByBookId;
    private Collection<BookLease> bookLeasesByBookEntityId;
    private Collection<BookReturn> bookReturnsByBookEntityId;

    @Basic
    @Column(name = "available")
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Id
    @Column(name = "book_entity_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getBookEntityId() {
        return bookEntityId;
    }

    public void setBookEntityId(int bookEntityId) {
        this.bookEntityId = bookEntityId;
    }

    @Basic
    @Column(name = "leased")
    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }

    @Basic
    @Column(name = "acquisition_date")
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (available != that.available) return false;
        if (bookEntityId != that.bookEntityId) return false;
        if (leased != that.leased) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (available ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + bookEntityId;
        result = 31 * result + (leased ? 1 : 0);
        result = 31 * result + (acquisitionDate != null ? acquisitionDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @OneToMany(mappedBy = "bookEntityByBookEntityId")
    public Collection<BookLease> getBookLeasesByBookEntityId() {
        return bookLeasesByBookEntityId;
    }

    public void setBookLeasesByBookEntityId(Collection<BookLease> bookLeasesByBookEntityId) {
        this.bookLeasesByBookEntityId = bookLeasesByBookEntityId;
    }

    @OneToMany(mappedBy = "bookEntityByBookEntityId")
    public Collection<BookReturn> getBookReturnsByBookEntityId() {
        return bookReturnsByBookEntityId;
    }

    public void setBookReturnsByBookEntityId(Collection<BookReturn> bookReturnsByBookEntityId) {
        this.bookReturnsByBookEntityId = bookReturnsByBookEntityId;
    }
}
