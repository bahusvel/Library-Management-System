package Persistance;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
public class Employee {
    private String firstname;
    private String lastname;
    private String role;
    private int employeeId;
    private Address address;
    private Collection<BookLease> bookLeases;
    private Collection<BookReturn> bookReturns;
    private Collection<ItemLease> itemLeases;
    private Collection<ItemReturn> itemReturns;

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Column(name = "firstname")
    @NotNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Column(name = "lastname")
    @NotNull
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Column(name = "role")
    @NotNull
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (address != null ? !address.equals(employee.address) : employee.address != null) return false;
        if (bookLeases != null ? !bookLeases.equals(employee.bookLeases) : employee.bookLeases != null)
            return false;
        if (bookReturns != null ? !bookReturns.equals(employee.bookReturns) : employee.bookReturns != null)
            return false;
        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
        if (itemLeases != null ? !itemLeases.equals(employee.itemLeases) : employee.itemLeases != null)
            return false;
        if (itemReturns != null ? !itemReturns.equals(employee.itemReturns) : employee.itemReturns != null)
            return false;
        if (lastname != null ? !lastname.equals(employee.lastname) : employee.lastname != null) return false;
        if (role != null ? !role.equals(employee.role) : employee.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + employeeId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (bookLeases != null ? bookLeases.hashCode() : 0);
        result = 31 * result + (bookReturns != null ? bookReturns.hashCode() : 0);
        result = 31 * result + (itemLeases != null ? itemLeases.hashCode() : 0);
        result = 31 * result + (itemReturns != null ? itemReturns.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<BookLease> getBookLeases() {
        return bookLeases;
    }

    public void setBookLeases(Collection<BookLease> bookLeases) {
        this.bookLeases = bookLeases;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<BookReturn> getBookReturns() {
        return bookReturns;
    }

    public void setBookReturns(Collection<BookReturn> bookReturns) {
        this.bookReturns = bookReturns;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<ItemLease> getItemLeases() {
        return itemLeases;
    }

    public void setItemLeases(Collection<ItemLease> itemLeases) {
        this.itemLeases = itemLeases;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<ItemReturn> getItemReturns() {
        return itemReturns;
    }

    public void setItemReturns(Collection<ItemReturn> itemReturns) {
        this.itemReturns = itemReturns;
    }
}
