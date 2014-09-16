package Persistance;

import imageUtils.DBIO;
import org.apache.solr.analysis.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
@AnalyzerDefs({
        @AnalyzerDef(name="projectionAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class)),

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
    private Integer pages;
    private String publisher;
    private String description;
    private Long barcode;
    private String isbn;
    private int edition = 1;
    private String category;
    private Double rating;
    private int bookId;
    private String summary;
    private Double price = 0.0;
    private byte[] image = DBIO.imageNotAvailable;
    private Collection<BookEntity> bookEntities;
    private Collection<String> author;
    private Collection<BookRequest> bookRequests;
    private Collection<BookReturn> bookReturns;

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
            @Field(name = "store_title", analyzer = @Analyzer(definition = "projectionAnalyzer"), store = Store.COMPRESS),
            @Field(name = "title", analyzer = @Analyzer(definition = "TokenizingLower")),
            @Field(name = "edgeTitle", analyzer = @Analyzer(definition = "autoEdge")),
            @Field(name = "ngramTitle", analyzer = @Analyzer(definition = "autoNgram")),
    })
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
    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
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
    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
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
    @NotNull
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
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                '}';
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void imageToDatabase(File f){
        image = DBIO.imageFromFile(f);
    }

    public BufferedImage imageFromDatabase(){
        BufferedImage ret = DBIO.bImageFromArray(image);
        if (ret != null) return ret;
        return DBIO.bImageNotAvailable;
    }

    // NOTE TO SELF
    // DO NOT GENERATE equals AND hashCode for Cascaded entities.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (edition != book.edition) return false;
        if (barcode != null ? !barcode.equals(book.barcode) : book.barcode != null) return false;
        if (category != null ? !category.equals(book.category) : book.category != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
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
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + edition;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + bookId;
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "book")
    public Collection<BookReturn> getBookReturns() {
        return bookReturns;
    }

    public void setBookReturns(Collection<BookReturn> bookReturns) {
        this.bookReturns = bookReturns;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<BookEntity> getBookEntities() {
        return bookEntities;
    }

    public void setBookEntities(Collection<BookEntity> bookEntities) {
        this.bookEntities = bookEntities;
    }

    @OneToMany(mappedBy = "book")
    public Collection<BookRequest> getBookRequests() {
        return bookRequests;
    }

    public void setBookRequests(Collection<BookRequest> bookRequests) {
        this.bookRequests = bookRequests;
    }

}
