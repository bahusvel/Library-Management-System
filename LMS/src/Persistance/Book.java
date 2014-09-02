package Persistance;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class Book {
    private String title;
    private String authors;
    private Date releaseDate;
    private Integer pages;
    private String publisher;
    private String description;
    private Long barcode;
    private String isbn;
    private Integer edition;
    private String category;
    private Double rating;
    private int bookId;
    private String imageFpath;
    private String summary;
    private Double price;
    private Collection<BookEntity> bookEntitiesByBookId;

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "authors")
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Basic
    @Column(name = "release_date")
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "pages")
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Basic
    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "barcode")
    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "edition")
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "rating")
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "image_fpath")
    public String getImageFpath() {
        return imageFpath;
    }

    public void setImageFpath(String imageFpath) {
        this.imageFpath = imageFpath;
    }

    @Basic
    @Column(name = "summary")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Basic
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        if (barcode != null ? !barcode.equals(book.barcode) : book.barcode != null) return false;
        if (category != null ? !category.equals(book.category) : book.category != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (edition != null ? !edition.equals(book.edition) : book.edition != null) return false;
        if (imageFpath != null ? !imageFpath.equals(book.imageFpath) : book.imageFpath != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (pages != null ? !pages.equals(book.pages) : book.pages != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (rating != null ? !rating.equals(book.rating) : book.rating != null) return false;
        if (releaseDate != null ? !releaseDate.equals(book.releaseDate) : book.releaseDate != null) return false;
        if (summary != null ? !summary.equals(book.summary) : book.summary != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (edition != null ? edition.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + bookId;
        result = 31 * result + (imageFpath != null ? imageFpath.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<BookEntity> getBookEntitiesByBookId() {
        return bookEntitiesByBookId;
    }

    public void setBookEntitiesByBookId(Collection<BookEntity> bookEntitiesByBookId) {
        this.bookEntitiesByBookId = bookEntitiesByBookId;
    }
}
