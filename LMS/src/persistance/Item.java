package persistance;

import lombok.*;
import org.hibernate.search.annotations.*;
import persistance.base.LeasableItem;
import util.DBIO;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by denislavrov on 9/2/14.
 */
@Data
@EqualsAndHashCode(callSuper = true, exclude = "image")
@Entity
@Indexed
public class Item extends LeasableItem<Item> {
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    private String name;
    @Field
    @Analyzer(definition = "TokenizingLower")
    private String description;
    @Field(analyze = Analyze.NO)
    private String condition;
    @Field(analyze = Analyze.NO)
    private String category;
    @Embedded
    private DBImage image = new DBImage();

}
