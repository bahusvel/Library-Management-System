package Persistance;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class Member {
    private String firstname;
    private String lastname;
    private Date dob;
    private Date registrationDate;
    private String username;
    private String country;
    private String city;
    private String postalCode;
    private String address1;
    private String address2;
    private String address3;
    private String email;
    private String phoneNumber;
    private int memberId;
    private Double balance;
    private Collection<BookLease> bookLeasesByMemberId;
    private Collection<BookReturn> bookReturnsByMemberId;
    private Collection<ItemLease> itemLeasesByMemberId;
    private Collection<ItemReturn> itemReturnsByMemberId;

    @Basic
    @Column(name = "firstname")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Basic
    @Column(name = "registration_date")
    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "Address_1")
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "Address_2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "Address_3")
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "member_id")
    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "balance")
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberId != member.memberId) return false;
        if (address1 != null ? !address1.equals(member.address1) : member.address1 != null) return false;
        if (address2 != null ? !address2.equals(member.address2) : member.address2 != null) return false;
        if (address3 != null ? !address3.equals(member.address3) : member.address3 != null) return false;
        if (balance != null ? !balance.equals(member.balance) : member.balance != null) return false;
        if (city != null ? !city.equals(member.city) : member.city != null) return false;
        if (country != null ? !country.equals(member.country) : member.country != null) return false;
        if (dob != null ? !dob.equals(member.dob) : member.dob != null) return false;
        if (email != null ? !email.equals(member.email) : member.email != null) return false;
        if (firstname != null ? !firstname.equals(member.firstname) : member.firstname != null) return false;
        if (lastname != null ? !lastname.equals(member.lastname) : member.lastname != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(member.phoneNumber) : member.phoneNumber != null) return false;
        if (postalCode != null ? !postalCode.equals(member.postalCode) : member.postalCode != null) return false;
        if (registrationDate != null ? !registrationDate.equals(member.registrationDate) : member.registrationDate != null)
            return false;
        if (username != null ? !username.equals(member.username) : member.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (registrationDate != null ? registrationDate.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + memberId;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "memberByMemberId")
    public Collection<BookLease> getBookLeasesByMemberId() {
        return bookLeasesByMemberId;
    }

    public void setBookLeasesByMemberId(Collection<BookLease> bookLeasesByMemberId) {
        this.bookLeasesByMemberId = bookLeasesByMemberId;
    }

    @OneToMany(mappedBy = "memberByMemberId")
    public Collection<BookReturn> getBookReturnsByMemberId() {
        return bookReturnsByMemberId;
    }

    public void setBookReturnsByMemberId(Collection<BookReturn> bookReturnsByMemberId) {
        this.bookReturnsByMemberId = bookReturnsByMemberId;
    }

    @OneToMany(mappedBy = "memberByMemberId")
    public Collection<ItemLease> getItemLeasesByMemberId() {
        return itemLeasesByMemberId;
    }

    public void setItemLeasesByMemberId(Collection<ItemLease> itemLeasesByMemberId) {
        this.itemLeasesByMemberId = itemLeasesByMemberId;
    }

    @OneToMany(mappedBy = "memberByMemberId")
    public Collection<ItemReturn> getItemReturnsByMemberId() {
        return itemReturnsByMemberId;
    }

    public void setItemReturnsByMemberId(Collection<ItemReturn> itemReturnsByMemberId) {
        this.itemReturnsByMemberId = itemReturnsByMemberId;
    }
}