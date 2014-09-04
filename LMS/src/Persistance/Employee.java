package Persistance;

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
    private Collection<BookLease> bookLeasesByEmployeeId;
    private Collection<BookReturn> bookReturnsByEmployeeId;
    private Collection<ItemLease> itemLeasesByEmployeeId;
    private Collection<ItemReturn> itemReturnsByEmployeeId;

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
    @Column(name = "role")
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
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (employeeId != employee.employeeId) return false;
        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
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
        return result;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<BookLease> getBookLeasesByEmployeeId() {
        return bookLeasesByEmployeeId;
    }

    public void setBookLeasesByEmployeeId(Collection<BookLease> bookLeasesByEmployeeId) {
        this.bookLeasesByEmployeeId = bookLeasesByEmployeeId;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<BookReturn> getBookReturnsByEmployeeId() {
        return bookReturnsByEmployeeId;
    }

    public void setBookReturnsByEmployeeId(Collection<BookReturn> bookReturnsByEmployeeId) {
        this.bookReturnsByEmployeeId = bookReturnsByEmployeeId;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<ItemLease> getItemLeasesByEmployeeId() {
        return itemLeasesByEmployeeId;
    }

    public void setItemLeasesByEmployeeId(Collection<ItemLease> itemLeasesByEmployeeId) {
        this.itemLeasesByEmployeeId = itemLeasesByEmployeeId;
    }

    @OneToMany(mappedBy = "employee")
    public Collection<ItemReturn> getItemReturnsByEmployeeId() {
        return itemReturnsByEmployeeId;
    }

    public void setItemReturnsByEmployeeId(Collection<ItemReturn> itemReturnsByEmployeeId) {
        this.itemReturnsByEmployeeId = itemReturnsByEmployeeId;
    }
}
