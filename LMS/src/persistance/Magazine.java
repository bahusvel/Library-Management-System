package persistance;

import lombok.*;
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
@Data
@EqualsAndHashCode(callSuper = true, exclude = "magazineEditions")
@Entity
@Indexed
public class Magazine extends AbstractItem<Magazine> {
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    private String title;
    @Field
    private String publisher;
    private String frequency;
    @Field(analyze = Analyze.NO)
    private String language;
    @Field
    private String isbn;
    @OneToMany(mappedBy = "abstractItem", cascade = CascadeType.ALL)
    private Collection<Edition<Magazine>> magazineEditions = new ArrayList<>();
}
