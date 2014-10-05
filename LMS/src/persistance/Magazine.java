package persistance;

import org.hibernate.search.annotations.*;
import persistance.base.AbstractItem;
import persistance.base.Edition;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
public class Magazine extends AbstractItem<Magazine> {
    private String title;
    private String publisher;
    private String frequency;
    private String language;
    private String isbn;
    private Collection<Edition<Magazine>> magazineEditions = new ArrayList<>();

    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Field
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }


    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }


    @Field(analyze = Analyze.NO)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    @Field
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @OneToMany(mappedBy = "abstractItem", cascade = CascadeType.ALL)
    public Collection<Edition<Magazine>> getMagazineEditions() {
        return magazineEditions;
    }

    public void setMagazineEditions(Collection<Edition<Magazine>> magazineEditions) {
        magazineEditions.forEach(m -> m.setAbstractItem(this));
        this.magazineEditions = magazineEditions;
    }
}
