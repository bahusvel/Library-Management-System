package Persistence;

import java.util.Collection;

/**
 * Created by denislavrov on 9/2/14.
 */
public class Employee {
    private String firstname;
    private String lastname;
    private String role;
    private int employeeId;
    private Collection<BookLease> bookLeasesByEmployeeId;
    private Collection<BookReturn> bookReturnsByEmployeeId;
    private Collection<ItemLease> itemLeasesByEmployeeId;
    private Collection<ItemReturn> itemReturnsByEmployeeId;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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

    public Collection<BookLease> getBookLeasesByEmployeeId() {
        return bookLeasesByEmployeeId;
    }

    public void setBookLeasesByEmployeeId(Collection<BookLease> bookLeasesByEmployeeId) {
        this.bookLeasesByEmployeeId = bookLeasesByEmployeeId;
    }

    public Collection<BookReturn> getBookReturnsByEmployeeId() {
        return bookReturnsByEmployeeId;
    }

    public void setBookReturnsByEmployeeId(Collection<BookReturn> bookReturnsByEmployeeId) {
        this.bookReturnsByEmployeeId = bookReturnsByEmployeeId;
    }

    public Collection<ItemLease> getItemLeasesByEmployeeId() {
        return itemLeasesByEmployeeId;
    }

    public void setItemLeasesByEmployeeId(Collection<ItemLease> itemLeasesByEmployeeId) {
        this.itemLeasesByEmployeeId = itemLeasesByEmployeeId;
    }

    public Collection<ItemReturn> getItemReturnsByEmployeeId() {
        return itemReturnsByEmployeeId;
    }

    public void setItemReturnsByEmployeeId(Collection<ItemReturn> itemReturnsByEmployeeId) {
        this.itemReturnsByEmployeeId = itemReturnsByEmployeeId;
    }
}
