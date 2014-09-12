package Persistance;


import com.sun.istack.internal.NotNull;
import org.apache.solr.analysis.*;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
@AnalyzerDef(name="NameAnalyzer",
tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class),
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
    private Address address;
    private String email;
    private String phoneNumber;
    private int memberId;
    private double balance = 0.0;
    private Collection<BookLease> bookLeasesByMemberId;
    private Collection<BookReturn> bookReturnsByMemberId;
    private Collection<ItemLease> itemLeasesByMemberId;
    private Collection<ItemReturn> itemReturnsByMemberId;


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


    @Column(name = "email")
    @NotNull
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
        if (bookLeasesByMemberId != null ? !bookLeasesByMemberId.equals(member.bookLeasesByMemberId) : member.bookLeasesByMemberId != null)
            return false;
        if (bookReturnsByMemberId != null ? !bookReturnsByMemberId.equals(member.bookReturnsByMemberId) : member.bookReturnsByMemberId != null)
            return false;
        if (dob != null ? !dob.equals(member.dob) : member.dob != null) return false;
        if (email != null ? !email.equals(member.email) : member.email != null) return false;
        if (firstname != null ? !firstname.equals(member.firstname) : member.firstname != null) return false;
        if (itemLeasesByMemberId != null ? !itemLeasesByMemberId.equals(member.itemLeasesByMemberId) : member.itemLeasesByMemberId != null)
            return false;
        if (itemReturnsByMemberId != null ? !itemReturnsByMemberId.equals(member.itemReturnsByMemberId) : member.itemReturnsByMemberId != null)
            return false;
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
        result = 31 * result + (bookLeasesByMemberId != null ? bookLeasesByMemberId.hashCode() : 0);
        result = 31 * result + (bookReturnsByMemberId != null ? bookReturnsByMemberId.hashCode() : 0);
        result = 31 * result + (itemLeasesByMemberId != null ? itemLeasesByMemberId.hashCode() : 0);
        result = 31 * result + (itemReturnsByMemberId != null ? itemReturnsByMemberId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Member{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }


    @OneToMany(mappedBy = "member")
    public Collection<BookLease> getBookLeasesByMemberId() {
        return bookLeasesByMemberId;
    }

    public void setBookLeasesByMemberId(Collection<BookLease> bookLeasesByMemberId) {
        this.bookLeasesByMemberId = bookLeasesByMemberId;
    }

    @OneToMany(mappedBy = "member")
    public Collection<BookReturn> getBookReturnsByMemberId() {
        return bookReturnsByMemberId;
    }

    public void setBookReturnsByMemberId(Collection<BookReturn> bookReturnsByMemberId) {
        this.bookReturnsByMemberId = bookReturnsByMemberId;
    }

    @OneToMany(mappedBy = "member")
    public Collection<ItemLease> getItemLeasesByMemberId() {
        return itemLeasesByMemberId;
    }

    public void setItemLeasesByMemberId(Collection<ItemLease> itemLeasesByMemberId) {
        this.itemLeasesByMemberId = itemLeasesByMemberId;
    }

    @OneToMany(mappedBy = "member")
    public Collection<ItemReturn> getItemReturnsByMemberId() {
        return itemReturnsByMemberId;
    }

    public void setItemReturnsByMemberId(Collection<ItemReturn> itemReturnsByMemberId) {
        this.itemReturnsByMemberId = itemReturnsByMemberId;
    }
}
