package persistance.inheritance;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class InheritanceBookEntity extends persistance.lease.Entity<InheritanceBook> {
    private String location;
    private Date acquisitionDate;
    private String suppliedBy;

    public InheritanceBookEntity(){

    }


    public InheritanceBookEntity(InheritanceBook book, Date acquisitionDate) {
        this.leasable = book;
        this.acquisitionDate = acquisitionDate;
    }


    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    @Column(name = "acquisition_date")
    @Temporal(TemporalType.DATE)
    @NotNull
    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Column(name = "supplied_by")
    public String getSuppliedBy() {
        return suppliedBy;
    }

    public void setSuppliedBy(String suppliedBy) {
        this.suppliedBy = suppliedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InheritanceBookEntity)) return false;

        InheritanceBookEntity that = (InheritanceBookEntity) o;

        if (available != that.available) return false;
        if (leased != that.leased) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (suppliedBy != null ? !suppliedBy.equals(that.suppliedBy) : that.suppliedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (available ? 1 : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (leased ? 1 : 0);
        result = 31 * result + (acquisitionDate != null ? acquisitionDate.hashCode() : 0);
        result = 31 * result + (suppliedBy != null ? suppliedBy.hashCode() : 0);
        return result;
    }


}
