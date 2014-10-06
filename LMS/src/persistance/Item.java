package persistance;

import lombok.*;
import org.hibernate.search.annotations.*;
import persistance.base.LeasableItem;
import util.DBIO;

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
    private byte[] image = DBIO.imageNotAvailable;


    public void imageToDatabase(File f){
        image = DBIO.imageFromFile(f);
    }

    public BufferedImage imageFromDatabase(){
        BufferedImage ret = DBIO.bImageFromArray(image);
        if (ret != null) return ret;
        return DBIO.bImageNotAvailable;
    }


}
