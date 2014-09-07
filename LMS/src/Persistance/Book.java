package Persistance;

import com.sun.istack.internal.NotNull;
import org.apache.solr.analysis.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
@AnalyzerDefs({
        @AnalyzerDef(name = "TokenizingLower",
                tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
                filters = {
                        @TokenFilterDef(factory = PatternReplaceFilterFactory.class,
                        params = {
                                @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                                @Parameter(name = "replacement", value = " "),
                                @Parameter(name = "replace", value = "all") }),
                        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                        @TokenFilterDef(factory = StopFilterFactory.class),
                        @TokenFilterDef(factory = SnowballPorterFilterFactory.class,
                                params = {@Parameter(name = "language", value = "English")})
                }),

        @AnalyzerDef(name="autoEdge", // analyzer for auto suggestions
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = StopFilterFactory.class),
                @TokenFilterDef(factory = EdgeNGramFilterFactory.class,
                params = {
                        @Parameter(name = "minGramSize", value = "3"), // prediction lower boundary
                        @Parameter(name = "maxGramSize", value = "50") // prediction upper boundary
                })
        }),
        @AnalyzerDef(name="autoNgram", // analyzer for auto suggestions
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = WordDelimiterFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class,
                params = {
                        @Parameter(name = "minGramSize", value = "3"), // prediction lower boundary
                        @Parameter(name = "maxGramSize", value = "5")
                }),
                @TokenFilterDef(factory = PatternReplaceFilterFactory.class,
                params = {
                        @Parameter(name = "pattern",value = "([^a-zA-Z0-9\\.])"),
                        @Parameter(name = "replacement", value = " "),
                        @Parameter(name = "replace", value = "all") })

        })

})
public class Book {
    /*
    Annotation Summary:
    @Transient -                Fields that are not persisted.
    @Entity -                   Classes that will be persisted.
    @Indexed -                  Tells Search to index this entity.
    @IndexedEmbedded -          Tells Search to index embedded Entity.
    @ElementCollection -        Used to store Collections in a linked table.
    @Column -                   Establishes relationship of field with database field.
    @Field -                    Tells search that this Field needs to be indexed.
    @Temporal -                 Data that is time or date or timestamp.
    @Id -                       Primary key.
    @GeneratedValue -           Automatic surrogate primary key generation.
    @[One|Many]To[One|Many] -   Relationship Types.
     */

    private String title;
    private Date releaseDate;
    private int pages;
    private String publisher;
    private String description;
    private long barcode;
    private String isbn;
    private int edition;
    private String category;
    private double rating;
    private int bookId;
    private String imageFpath;
    private String summary;
    private double price;
    private Collection<BookEntity> bookEntities;
    private Collection<String> author;

   /*
   Hibernate was a little annoying here.
   Basically the documentations specifies that Collections are indexed with @IndexedEmbedded
   But search cannot find field named author
   For that reason you have to specify field flag on it as well
   Otherwise it wont work.
    */

    @Field(name="author")
    @IndexedEmbedded
    @ElementCollection(fetch = FetchType.EAGER) // Specify this if you want Hibernate to fetch linked data.
    public Collection<String> getAuthor() {
        return author;
    }

    public void setAuthor(Collection<String> author) {
        this.author = author;
    }

    @Column(name = "title")
    @NotNull
    @Fields({
            @Field(name = "title"),
            @Field(name = "edgeTitle", analyzer = @Analyzer(definition = "autoEdge")),
            @Field(name = "ngramTitle", analyzer = @Analyzer(definition = "autoNgram")),
    })
    @Analyzer(definition = "TokenizingLower")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "release_date")
    @Temporal(TemporalType.DATE)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Column(name = "pages")
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Column(name = "publisher")
    @Field
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name = "description")
    @Field
    @Analyzer(definition = "TokenizingLower")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "barcode")
    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    @Column(name = "isbn", unique = true)
    @Field
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "edition")
    @Field
    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY) // Could also try GenerationType.TABLE
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Column(name = "image_fpath")
    public String getImageFpath() {
        return imageFpath;
    }

    public void setImageFpath(String imageFpath) {
        this.imageFpath = imageFpath;
    }

    @Column(name = "summary")
    @Field
    @Analyzer(definition = "TokenizingLower")
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (barcode != book.barcode) return false;
        if (bookId != book.bookId) return false;
        if (edition != book.edition) return false;
        if (pages != book.pages) return false;
        if (Double.compare(book.price, price) != 0) return false;
        if (Double.compare(book.rating, rating) != 0) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (bookEntities != null ? !bookEntities.equals(book.bookEntities) : book.bookEntities != null) return false;
        if (category != null ? !category.equals(book.category) : book.category != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (imageFpath != null ? !imageFpath.equals(book.imageFpath) : book.imageFpath != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (publisher != null ? !publisher.equals(book.publisher) : book.publisher != null) return false;
        if (releaseDate != null ? !releaseDate.equals(book.releaseDate) : book.releaseDate != null) return false;
        if (summary != null ? !summary.equals(book.summary) : book.summary != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + pages;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (barcode ^ (barcode >>> 32));
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + edition;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + bookId;
        result = 31 * result + (imageFpath != null ? imageFpath.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (bookEntities != null ? bookEntities.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "book", cascade = {CascadeType.ALL})
    public Collection<BookEntity> getBookEntitiesbookEntities() {
        return bookEntities;
    }

    public void setBookEntitiesbookEntities(Collection<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }
}
