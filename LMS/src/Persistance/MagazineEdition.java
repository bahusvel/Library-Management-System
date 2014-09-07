package Persistance;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "magazine_edition", schema = "public", catalog = "librarymanagementsystem")
public class MagazineEdition {
    private int magEditionId;
    private Date editionDate;
    private String editionTitle;
    private Magazine magazine;
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
        this.magazineEntities = magazineEntities;
    }
}
