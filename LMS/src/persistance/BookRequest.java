package persistance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by denislavrov on 9/12/14.
 */
@Entity
@Table(name = "book_request", schema = "public", catalog = "inheritance")
public class BookRequest {
    private int requestid;
    private String supplier;
    private String uri;
    private int quantity = 1;
    private Book book;

    @Id
    @Column(name = "requestid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    @Basic
    @Column(name = "supplier")
    @NotNull
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @Column(name = "quantity")
    @NotNull
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookRequest that = (BookRequest) o;

        if (quantity != that.quantity) return false;
        if (requestid != that.requestid) return false;
        if (supplier != null ? !supplier.equals(that.supplier) : that.supplier != null) return false;
        if (uri != null ? !uri.equals(that.uri) : that.uri != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = requestid;
        result = 31 * result + (supplier != null ? supplier.hashCode() : 0);
        result = 31 * result + (uri != null ? uri.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bookid", referencedColumnName = "book_id", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
