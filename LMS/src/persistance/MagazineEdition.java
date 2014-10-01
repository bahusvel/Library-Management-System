package persistance;

import util.DBIO;

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
@Table(name = "magazine_edition", schema = "public", catalog = "inheritance")
public class MagazineEdition {
    private int magEditionId;
    private Date editionDate;
    private String editionTitle;
    private Magazine magazine;
    private byte[] image = DBIO.imageNotAvailable;
    private Collection<MagazineEntity> magazineEntities;

    @Id
    @Column(name = "mag_edition_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getMagEditionId() {
        return magEditionId;
    }

    public void setMagEditionId(int magEditionId) {
        this.magEditionId = magEditionId;
    }


    @Column(name = "edition_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getEditionDate() {
        return editionDate;
    }

    public void setEditionDate(Date editionDate) {
        this.editionDate = editionDate;
    }


    @Column(name = "edition_title")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MagazineEdition that = (MagazineEdition) o;

        if (magEditionId != that.magEditionId) return false;
        if (editionDate != null ? !editionDate.equals(that.editionDate) : that.editionDate != null) return false;
        if (editionTitle != null ? !editionTitle.equals(that.editionTitle) : that.editionTitle != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magEditionId;
        result = 31 * result + (editionDate != null ? editionDate.hashCode() : 0);
        result = 31 * result + (editionTitle != null ? editionTitle.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "magazine_id", referencedColumnName = "magazine_id", nullable = false)
    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    @OneToMany(mappedBy = "magazineEdition")
    public Collection<MagazineEntity> getMagazineEntities() {
        return magazineEntities;
    }

    public void setMagazineEntities(Collection<MagazineEntity> magazineEntities) {
        magazineEntities.forEach(m -> m.setMagazineEdition(this));
        this.magazineEntities = magazineEntities;
    }
}
