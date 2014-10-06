package persistance.base;

import lombok.*;
import util.DBIO;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 10/1/14.
 */
@Data
@javax.persistence.Entity
@EqualsAndHashCode(callSuper = true, exclude = {"entities", "abstractItem"})
public class Edition<T extends AbstractItem<T>> extends AbstractItem<T>{
    @Temporal(TemporalType.DATE)
    @NotNull
    protected Date editionDate;
    protected String editionTitle;
    @ManyToOne
    protected AbstractItem<T> abstractItem;
    protected byte[] image = DBIO.imageNotAvailable;
    @OneToMany(mappedBy = "abstractItem")
    protected Collection<Entity<T>> entities;


    public void imageToDatabase(File f){
        image = DBIO.imageFromFile(f);
    }

    public BufferedImage imageFromDatabase(){
        BufferedImage ret = DBIO.bImageFromArray(image);
        if (ret != null) return ret;
        return DBIO.bImageNotAvailable;
    }


}
