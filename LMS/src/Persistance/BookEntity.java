package Persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "book_entity", schema = "public", catalog = "librarymanagementsystem")
public class BookEntity {
    private boolean available = true;
    private String location;
    private int bookEntityId;
    private boolean leased = false;
    private Date acquisitionDate;
    private String suppliedBy;
    private Book book;
    private BookLease bookLease;
    private Collection<BookReturn> bookReturns;

    public BookEntity(){

    }


    public BookEntity(Book book, Date acquisitionDate) {
        this.book = book;
        this.acquisitionDate = acquisitionDate;
    }

    @Column(name = "available")
    @NotNull
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


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


    @Column(name = "leased")
    @NotNull
    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }


    @Column(name = "acquisition_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Column(name = "supplied_by")
    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;

        BookEntity that = (BookEntity) o;

        if (available != that.available) return false;
        if (bookEntityId != that.bookEntityId) return false;
        if (leased != that.leased) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (suppliedBy != null ? !suppliedBy.equals(that.suppliedBy) : that.suppliedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (available ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + bookEntityId;
        result = 31 * result + (leased ? 1 : 0);
        result = 31 * result + (acquisitionDate != null ? acquisitionDate.hashCode() : 0);
        result = 31 * result + (suppliedBy != null ? suppliedBy.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @OneToOne(mappedBy = "bookEntity")
    public BookLease getBookLease() {
        return bookLease;
    }

    public void setBookLease(BookLease bookLease) {
        this.bookLease = bookLease;
    }

    @OneToMany(mappedBy = "bookEntity")
    public Collection<BookReturn> getBookReturns() {
        return bookReturns;
    }

    public void setBookReturns(Collection<BookReturn> bookReturns) {
        this.bookReturns = bookReturns;
    }
}
