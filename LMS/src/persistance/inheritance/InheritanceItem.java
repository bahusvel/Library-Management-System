package persistance.inheritance;

import org.hibernate.search.annotations.*;
import persistance.base.Leasable;
import util.DBIO;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
public class InheritanceItem extends Leasable<InheritanceItem>{
    private String name;
    private String description;
    private String condition;
    private String category;
    private byte[] image = DBIO.imageNotAvailable;


    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "TokenizingLower")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Field
    @Analyzer(definition = "TokenizingLower")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Field(analyze = Analyze.NO)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }


    @Field(analyze = Analyze.NO)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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


}
