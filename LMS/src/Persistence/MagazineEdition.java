package Persistence;

import java.sql.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
public class MagazineEdition {
    private int magEditionId;
    private Date editionDate;
    private String editionTitle;
    private Magazine magazineByMagazineId;
    private Collection<MagazineEntity> magazineEntitiesByMagEditionId;

    public int getMagEditionId() {
        return magEditionId;
    }

    public void setMagEditionId(int magEditionId) {
        this.magEditionId = magEditionId;
    }

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

    public Magazine getMagazineByMagazineId() {
        return magazineByMagazineId;
    }

    public void setMagazineByMagazineId(Magazine magazineByMagazineId) {
        this.magazineByMagazineId = magazineByMagazineId;
    }

    public Collection<MagazineEntity> getMagazineEntitiesByMagEditionId() {
        return magazineEntitiesByMagEditionId;
    }

    public void setMagazineEntitiesByMagEditionId(Collection<MagazineEntity> magazineEntitiesByMagEditionId) {
        this.magazineEntitiesByMagEditionId = magazineEntitiesByMagEditionId;
    }
}
