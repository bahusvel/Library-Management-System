package persistance;


import lombok.*;
import managers.notification.Notification;
import managers.notification.NotificationManager;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.validator.constraints.Email;
import persistance.base.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by denislavrov on 9/2/14.
 */
@Data
@EqualsAndHashCode(exclude = {"leases","returns","visits","currentVisit"})
@Entity
@Indexed
@AnalyzerDef(name="NameAnalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = PhoneticFilterFactory.class,
                params = { @Parameter(name="encoder", value="DOUBLEMETAPHONE")})
})

public class Member {
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "NameAnalyzer")
    private String firstname;
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "NameAnalyzer")
    private String lastname;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dob;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date registrationDate = new Date(); // present time
    @NotNull
    @Field
    private String username;
    @Embedded
    private Address address = new Address();
    @NotNull
    @Email
    @Field
    private String email;
    @Field
    private String phoneNumber;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int memberId;
    @NotNull
    private double balance = 0.0;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min=0, max = 5)
    private Collection<Lease> leases;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Collection<Return> returns;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private Collection<Visit> visits;
    @OneToOne(cascade = CascadeType.ALL)
    private Visit currentVisit;

    // JAVAFX PROPERTIES START
    @Transient
    public LocalDate pDOB = LocalDate.now();


    public LocalDate getPDOB() {
        return pDOB;
    }
    public void setPDOB(LocalDate pDOB) {
        this.pDOB = pDOB;
    }
    // JAVAFX PROPERTIES END

    public Member() {
    }

    public Member(String firstname, String lastname, Date dob, String username, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.username = username;
        this.email = email;
        this.password = password;
    }


    public String signIn(){
        Date cTimeStamp = new Date();
        Visit visit = new Visit(this, cTimeStamp, cTimeStamp, true);
        visits.add(visit);
        currentVisit = visit;
        return "Sign In OK";
    }

    public String signOut(){
        currentVisit.setCurrent(false);
        currentVisit.setExittime(new Date());
        currentVisit = null;
        return "Sign Out OK";
    }


    public <T extends AbstractItem<T>> String leaseItem(LeasableItem<T> item, Employee employee, Date until){
        if (leases.size() > 4) return "Limit of 5 leases per Member reached.";

        LeasableEntity<T> leaseEntity = null;
        for(persistance.base.Entity<T> entity : item.getEntities()){
            if (entity.isAvailable() && !((LeasableEntity<T>)entity).isLeased()){
                leaseEntity = (LeasableEntity<T>)entity;
                break;
            }
        }
        if (leaseEntity == null) return "Item wasn’t available";
        if (currentVisit == null) return "Customer hasn't signed in";
        Lease<T> lease = new Lease<>(new Date(), until, this, employee, leaseEntity ,currentVisit);
        leaseEntity.setLease(lease);
        leaseEntity.setLeased(true);
        leases.add(lease);
        return "Lease was successful";
    }

    public <T extends AbstractItem<T>> String returnItem(LeasableEntity<T> entity, Employee employee){
        Lease<T> lease = entity.getLease();
        if (!lease.getMember().equals(this)) return "You did not lease that book";
        Date dueDate = new Date(lease.getDueDate().getTime());
        Date cDate = new Date();
        Return<T> ireturn = new Return<>(lease, employee);
        if (dueDate.before(cDate)) {
            LocalDate dueLocal = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysOverdue = LocalDate.now().until(dueLocal, ChronoUnit.DAYS);
            ireturn.setAmmountCharged(-0.60 * daysOverdue);
            balance -= -0.60 * daysOverdue;
        }
        returns.add(ireturn);
        leases.remove(lease);
        entity.setLeased(false);
        return "Return successful";
    }

    public <T extends AbstractItem<T>> String looseItem(LeasableEntity<T> entity, Employee employee){
        Lease<T> lease = entity.getLease();
        if (!lease.getMember().equals(this)) return "You did not lease that book";
        Return<T> ireturn = new Return<>(lease, employee);
        ireturn.setLost(true);
        double price = entity.getAbstractItem().getPrice();
        ireturn.setAmmountCharged(price);
        balance -= price;
        returns.add(ireturn);
        leases.remove(lease);
        ((LeasableItem<T>)entity.getAbstractItem()).getEntities().remove(entity);
        return "Lost book acknowledge";
    }

    private List<AbstractItem> itemsOverdue(){
        ArrayList<AbstractItem> items = new ArrayList<>();
        Date today = new Date();
        leases.stream().filter(il -> il.getDueDate().before(today)).forEach(il -> items.add(il.getLeasableEntity().getAbstractItem()));
        return items;
    }

    public void expireNotify(){
        List<AbstractItem> expiredItems = itemsOverdue();
        StringBuilder items = new StringBuilder();
        if (!expiredItems.isEmpty()){
            items.append("Following items are past their due date:\n");
            expiredItems.forEach(b -> {
                items.append(b);
                items.append('\n');
            });
            items.append("You will be charged $0.6 every day for each of those items, until you return them\n\n");
        }

        StringBuilder message = new StringBuilder();
        message.append("Hi ").append(firstname).append(",\n\n")
                .append(items)
                .append("Best Regards, \n" +
                        "Your favorite library\n" +
                        "This is an automated email, do not reply to it.")
                .append('\n');



        Notification notification = new Notification("Library Reminder",message.toString(),email);
        NotificationManager.submitNotification(notification);
    }


}
