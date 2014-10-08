package persistance.base;

import lombok.*;
import persistance.DBImage;
import util.DBIO;

import javax.persistence.*;
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
    @Embedded
    private DBImage image = new DBImage();
    @OneToMany(mappedBy = "abstractItem")
    protected Collection<Entity<T>> entities;
}
