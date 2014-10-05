package persistance;

import persistance.base.Lease;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/12/14.
 */
@Entity
public class Visit {
    private long visitid;
    private Date entrytime;
    private Date exittime;
    private Collection<Lease> leases;
    private Member member;
    private boolean current = false;

    public Visit(){

    }

    public Visit(Member member, Date exittime, Date entrytime, boolean current) {
        this.member = member;
        this.exittime = exittime;
        this.entrytime = entrytime;
        this.current = current;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getVisitid() {
        return visitid;
    }

    public void setVisitid(long visitid) {
        this.visitid = visitid;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Date entrytime) {
        this.entrytime = entrytime;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getExittime() {
        return exittime;
    }

    public void setExittime(Date exittime) {
        this.exittime = exittime;
    }

    @NotNull
    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Visit visit = (Visit) o;

        if (visitid != visit.visitid) return false;
        if (entrytime != null ? !entrytime.equals(visit.entrytime) : visit.entrytime != null) return false;
        if (exittime != null ? !exittime.equals(visit.exittime) : visit.exittime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (visitid ^ (visitid >>> 32));
        result = 31 * result + (entrytime != null ? entrytime.hashCode() : 0);
        result = 31 * result + (exittime != null ? exittime.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "visit")
    public Collection<Lease> getLeases() {
        return leases;
    }

    public void setLeases(Collection<Lease> leases) {
        this.leases = leases;
    }

    @ManyToOne
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
