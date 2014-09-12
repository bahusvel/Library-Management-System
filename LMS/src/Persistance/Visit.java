package Persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by denislavrov on 9/12/14.
 */
@Entity
public class Visit {
    private long visitid;
    private Timestamp entrytime;
    private Timestamp exittime;
    private Collection<BookLease> bookLeases;
    private Collection<ItemLease> itemLeases;
    private Member member;
    private boolean current = false;

    @Id
    @Column(name = "visitid")
    public long getVisitid() {
        return visitid;
    }

    public void setVisitid(long visitid) {
        this.visitid = visitid;
    }

    @Column(name = "entrytime")
    @NotNull
    public Timestamp getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(Timestamp entrytime) {
        this.entrytime = entrytime;
    }

    @Column(name = "exittime")
    @NotNull
    public Timestamp getExittime() {
        return exittime;
    }

    public void setExittime(Timestamp exittime) {
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
    public Collection<BookLease> getBookLeases() {
        return bookLeases;
    }

    public void setBookLeases(Collection<BookLease> bookLeases) {
        this.bookLeases = bookLeases;
    }

    @OneToMany(mappedBy = "visit")
    public Collection<ItemLease> getItemLeases() {
        return itemLeases;
    }

    public void setItemLeases(Collection<ItemLease> itemLeases) {
        this.itemLeases = itemLeases;
    }

    @ManyToOne
    @JoinColumn(name = "memberid", referencedColumnName = "member_id", nullable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
