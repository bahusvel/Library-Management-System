package persistance;


import managers.notification.Notification;
import managers.notification.NotificationManager;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.PhoneticFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
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
@Entity
@Indexed
@AnalyzerDef(name="NameAnalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = PhoneticFilterFactory.class,
                params = {@Parameter(name="encoder", value="DOUBLEMETAPHONE")})
})
public class Member {
    private String firstname;
    private String lastname;
    private Date dob;
    private Date registrationDate = new Date(); // present time
    private String username;
    private Address address = new Address();
    private String email;
    private String phoneNumber;
    private int memberId;
    private double balance = 0.0;
    private String password;
    private Collection<BookLease> bookLeases;
    private Collection<BookReturn> bookReturns;
    private Collection<ItemLease> itemLeases;
    private Collection<ItemReturn> itemReturns;
    private Collection<Visit> visits;
    private Visit currentVisit;

    // JAVAFX PROPERTIES START
    public LocalDate pDOB = LocalDate.now();
    @Transient
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

    @Column(name = "firstname")
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "NameAnalyzer")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Column(name = "lastname")
    @NotNull
    @Field(store = Store.COMPRESS)
    @Analyzer(definition = "NameAnalyzer")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Column(name = "dob")
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "registration_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Column(name = "username", unique = true)
    @NotNull
    @Field
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email")
    @NotNull
    @Email
    @Field
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone_number")
    @Field
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Column(name = "balance")
    @NotNull
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (Double.compare(member.balance, balance) != 0) return false;
        if (memberId != member.memberId) return false;
        if (address != null ? !address.equals(member.address) : member.address != null) return false;
        if (dob != null ? !dob.equals(member.dob) : member.dob != null) return false;
        if (email != null ? !email.equals(member.email) : member.email != null) return false;
        if (firstname != null ? !firstname.equals(member.firstname) : member.firstname != null) return false;
        if (lastname != null ? !lastname.equals(member.lastname) : member.lastname != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(member.phoneNumber) : member.phoneNumber != null) return false;
        if (registrationDate != null ? !registrationDate.equals(member.registrationDate) : member.registrationDate != null)
            return false;
        if (username != null ? !username.equals(member.username) : member.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + memberId;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min=0, max = 5)
    public Collection<BookLease> getBookLeases() {
        return bookLeases;
    }

    public void setBookLeases(Collection<BookLease> bookLeases) {
        this.bookLeases = bookLeases;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    public Collection<BookReturn> getBookReturns() {
        return bookReturns;
    }

    public void setBookReturns(Collection<BookReturn> bookReturns) {
        this.bookReturns = bookReturns;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<ItemLease> getItemLeases() {
        return itemLeases;
    }

    public void setItemLeases(Collection<ItemLease> itemLeases) {
        this.itemLeases = itemLeases;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    public Collection<ItemReturn> getItemReturns() {
        return itemReturns;
    }

    public void setItemReturns(Collection<ItemReturn> itemReturns) {
        this.itemReturns = itemReturns;
    }

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    public Collection<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Collection<Visit> visits) {
        this.visits = visits;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Visit getCurrentVisit() {
        return currentVisit;
    }

    public void setCurrentVisit(Visit currentVisit) {
        this.currentVisit = currentVisit;
    }

    // ACTIVITY METHODS

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

    public String leaseBook(Book book, Employee employee, Date until){
        if (bookLeases.size() > 4) return "Limit of 5 books per Member reached.";

        BookEntity leaseEnity = null;
        for(BookEntity bookEntity : book.getBookEntities()){
            if (bookEntity.isAvailable() && !bookEntity.isLeased()){
                leaseEnity = bookEntity;
                break;
            }
        }
        if (leaseEnity == null) return "Book wasn’t available";
        if (currentVisit == null) return "Customer hasn't signed in";
        BookLease bookLease = new BookLease(new Date(), until, this, employee, leaseEnity ,currentVisit);
        leaseEnity.setBookLease(bookLease);
        leaseEnity.setLeased(true);
        bookLeases.add(bookLease);
        return "Lease was successful";
    }

    public String returnBook(BookEntity bookEntity, Employee employee){
        BookLease bookLease = bookEntity.getBookLease();
        if (!bookLease.getMember().equals(this)) return "You did not lease that book";
        Date dueDate = new Date(bookLease.getDueDate().getTime());
        Date cDate = new Date();
        BookReturn bookReturn = new BookReturn(bookLease, employee);
        if (dueDate.before(cDate)) {
            LocalDate dueLocal = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysOverdue = LocalDate.now().until(dueLocal, ChronoUnit.DAYS);
            bookReturn.setAmmountCharged(-0.60 * daysOverdue);
            balance -= -0.60 * daysOverdue;
        }
        bookReturns.add(bookReturn);
        bookLeases.remove(bookLease);
        bookEntity.setLeased(false);
        return "Return successful";
    }

    public String looseBook(BookEntity bookEntity, Employee employee){
        BookLease bookLease = bookEntity.getBookLease();
        if (!bookLease.getMember().equals(this)) return "You did not lease that book";
        BookReturn bookReturn = new BookReturn(bookLease, employee);
        bookReturn.setLost(true);
        double bookPrice = bookEntity.getBook().getPrice();
        bookReturn.setAmmountCharged(bookPrice);
        balance -= bookPrice;
        bookReturns.add(bookReturn);
        bookLeases.remove(bookLease);
        bookEntity.getBook().getBookEntities().remove(bookEntity);
        return "Lost book acknowledge";
    }

    public String leaseItem(Item item, Employee employee, Date until){
        if (itemLeases.size() > 4) return "Limit of 5 items per Member reached.";

        ItemEntity leaseEnity = null;
        for(ItemEntity itemEntity : item.getItemEntities()){
            if (itemEntity.isAvailable() && !itemEntity.isLeased()){
                leaseEnity = itemEntity;
                break;
            }
        }
        if (leaseEnity == null) return "Item wasn’t available";
        if (currentVisit == null) return "Customer hasn't signed in";
        ItemLease itemLease = new ItemLease(new Date(), until, this, employee, leaseEnity ,currentVisit);
        leaseEnity.setItemLease(itemLease);
        leaseEnity.setLeased(true);
        itemLeases.add(itemLease);
        return "Lease was successful";
    }

    public String returnItem(ItemEntity itemEntity, Employee employee){
        ItemLease itemLease = itemEntity.getItemLease();
        if (!itemLease.getMember().equals(this)) return "You did not lease that item";
        Date dueDate = new Date(itemLease.getDueDate().getTime());
        Date cDate = new Date();
        ItemReturn itemReturn = new ItemReturn(itemLease, employee);
        if (dueDate.before(cDate)) {
            LocalDate dueLocal = dueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysOverdue = LocalDate.now().until(dueLocal, ChronoUnit.DAYS);
            itemReturn.setAmmountCharged(-0.60 * daysOverdue);
            balance -= -0.60 * daysOverdue;
        }
        itemReturns.add(itemReturn);
        itemLeases.remove(itemLease);
        itemEntity.setLeased(false);
        return "Return successful";
    }

    public String looseItem(ItemEntity itemEntity, Employee employee){
        ItemLease itemLease = itemEntity.getItemLease();
        if (!itemLease.getMember().equals(this)) return "You did not lease that item";
        ItemReturn itemReturn = new ItemReturn(itemLease, employee);
        itemReturn.setLost(true);
        double itemPrice = itemEntity.getItem().getPrice();
        itemReturn.setAmmountCharged(itemPrice);
        balance -= itemPrice;
        itemReturns.add(itemReturn);
        itemLeases.remove(itemLease);
        itemEntity.getItem().getItemEntities().remove(itemEntity);
        return "Lost item acknowledge";
    }

    private List<Book> booksOverdue(){
        ArrayList<Book> books = new ArrayList<>();
        Date today = new Date();
        bookLeases.stream().filter(bl -> bl.getDueDate().before(today)).forEach(bl -> books.add(bl.getBookEntity().getBook()));
        return books;
    }

    private List<Item> itemsOverdue(){
        ArrayList<Item> items = new ArrayList<>();
        Date today = new Date();
        itemLeases.stream().filter(il -> il.getDueDate().before(today)).forEach(il -> items.add(il.getItemEntity().getItem()));
        return items;
    }

    public void expireNotify(){
        List<Book> expiredBooks = booksOverdue();
        StringBuilder books = new StringBuilder();
        if (!expiredBooks.isEmpty()){
            books.append("Following books are past their due date:\n");
            expiredBooks.forEach(b -> {
                books.append(b.getTitle());
                books.append('\n');
            });
            books.append("You will be charged $0.6 every day for each of those books, until you return them\n\n");
        }

        List<Item> expiredItems = itemsOverdue();
        StringBuilder items = new StringBuilder();
        if (!expiredItems.isEmpty()){
            items.append("Following items are past their due date:\n");
            expiredItems.forEach(b -> {
                items.append(b.getName());
                items.append('\n');
            });
            items.append("You will be charged $0.6 every day for each of those items, until you return them\n\n");
        }


        StringBuilder message = new StringBuilder();
        message.append("Hi ").append(firstname).append(",\n\n")
                .append(books)
                .append(items)
                .append("Best Regards, \n" +
                        "Your favorite library\n" +
                        "This is an automated email, do not reply to it.")
                .append('\n');



        Notification notification = new Notification("Library Reminder",message.toString(),email);
        NotificationManager.submitNotification(notification);
    }


}