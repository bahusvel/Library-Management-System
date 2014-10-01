package persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Table(name = "magazine_entity", schema = "public", catalog = "inheritance")
public class MagazineEntity {
    private int magEntityId;
    private boolean available = true;
    private MagazineEdition magazineEdition;


    @Column(name="acquistion_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getAcqusitionDate() {
        return acqusitionDate;
    }

    public void setAcqusitionDate(Date acqusitionDate) {
        this.acqusitionDate = acqusitionDate;
    }

    private Date acqusitionDate;

    @Id
    @Column(name = "mag_entity_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getMagEntityId() {
        return magEntityId;
    }

    public void setMagEntityId(int magEntityId) {
        this.magEntityId = magEntityId;
    }


    @Column(name = "available")
    @NotNull
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

    @ManyToOne
    @JoinColumn(name = "magazine_edition_id", referencedColumnName = "mag_edition_id", nullable = false)
    public MagazineEdition getMagazineEdition() {
        return magazineEdition;
    }

    public void setMagazineEdition(MagazineEdition magazineEdition) {
        this.magazineEdition = magazineEdition;
    }
}
