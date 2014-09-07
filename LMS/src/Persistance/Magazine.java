package Persistance;

import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
public class Magazine {
    private String title;
    private int magazineId;
    private String publisher;
    private String frequency;
    private String language;
    private String isbn;
    private Double price;
    private Collection<MagazineEdition> magazineEditions;


    @Column(name = "title")
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Id
    @Column(name = "magazine_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getMagazineId() {
        return magazineId;
    }

    public void setMagazineId(int magazineId) {
        this.magazineId = magazineId;
    }


    @Column(name = "publisher")
    @Field
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    @Column(name = "frequency")
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    @Column(name = "language")
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Column(name = "isbn")
    @Field
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


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

        Magazine magazine = (Magazine) o;

        if (magazineId != magazine.magazineId) return false;
        if (frequency != null ? !frequency.equals(magazine.frequency) : magazine.frequency != null) return false;
        if (isbn != null ? !isbn.equals(magazine.isbn) : magazine.isbn != null) return false;
        if (language != null ? !language.equals(magazine.language) : magazine.language != null) return false;
        if (price != null ? !price.equals(magazine.price) : magazine.price != null) return false;
        if (publisher != null ? !publisher.equals(magazine.publisher) : magazine.publisher != null) return false;
        if (title != null ? !title.equals(magazine.title) : magazine.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + magazineId;
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (frequency != null ? frequency.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "magazine")
    public Collection<MagazineEdition> getMagazineEditions() {
        return magazineEditions;
    }

    public void setMagazineEditions(Collection<MagazineEdition> magazineEditions) {
        this.magazineEditions = magazineEditions;
    }
}
