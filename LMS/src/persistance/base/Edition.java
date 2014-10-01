package persistance.base;

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
@javax.persistence.Entity
public class Edition<T extends AbstractItem<T>> extends AbstractItem<T>{
    protected Date editionDate;
    protected String editionTitle;
    protected AbstractItem<T> abstractItem;
    protected byte[] image = DBIO.imageNotAvailable;
    protected Collection<Entity<T>> entities;

    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }


    public String getEditionTitle() {
        return editionTitle;
    }

    public void setEditionTitle(String editionTitle) {
        this.editionTitle = editionTitle;
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

    @ManyToOne
    public AbstractItem<T>  getAbstractItem() {
        return abstractItem;
    }

    public void setAbstractItem(AbstractItem<T>  abstractItem) {
        this.abstractItem = abstractItem;
    }

    @OneToMany(mappedBy = "abstractItem")
    public Collection<Entity<T>> getMagazineEntities() {
        return entities;
    }

    public void setMagazineEntities(Collection<Entity<T>> entities) {
        this.entities = entities;
    }
}
