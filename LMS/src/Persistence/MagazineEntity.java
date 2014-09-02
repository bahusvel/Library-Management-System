package Persistence;

/**
 * Created by denislavrov on 9/2/14.
 */
public class MagazineEntity {
    private int magEntityId;
    private boolean available;
    private MagazineEdition magazineEditionByMagazineEditionId;

    public int getMagEntityId() {
        return magEntityId;
    }

    public void setMagEntityId(int magEntityId) {
        this.magEntityId = magEntityId;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MagazineEntity that = (MagazineEntity) o;

        if (available != that.available) return false;
        if (magEntityId != that.magEntityId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magEntityId;
        result = 31 * result + (available ? 1 : 0);
        return result;
    }

    public MagazineEdition getMagazineEditionByMagazineEditionId() {
        return magazineEditionByMagazineEditionId;
    }

    public void setMagazineEditionByMagazineEditionId(MagazineEdition magazineEditionByMagazineEditionId) {
        this.magazineEditionByMagazineEditionId = magazineEditionByMagazineEditionId;
    }
}
