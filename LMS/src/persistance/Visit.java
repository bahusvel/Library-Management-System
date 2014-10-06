package persistance;

import lombok.*;
import persistance.base.Lease;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/12/14.
 */
@Data
@EqualsAndHashCode(exclude = {"leases", "member"})
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long visitid;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrytime;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date exittime;
    @OneToMany(mappedBy = "visit")
    private Collection<Lease> leases;
    @ManyToOne
    private Member member;
    @NotNull
    private boolean current = false;

    public Visit(){

    }

    public Visit(Member member, Date exittime, Date entrytime, boolean current) {
        this.member = member;
        this.exittime = exittime;
        this.entrytime = entrytime;
        this.current = current;
    }


}
