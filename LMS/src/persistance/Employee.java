package persistance;

import org.hibernate.search.annotations.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Entity
@Indexed
public class Employee {
    private String firstname;
    private String lastname;
    private String username;
    private EmployeeRole role;
    private int employeeId;
    private Address address;
    private String password;
    private Collection<BookLease> bookLeases;
    private Collection<BookReturn> bookReturns;
    private Collection<ItemLease> itemLeases;
    private Collection<ItemReturn> itemReturns;

    public enum EmployeeRole{ADMIN, CASHIER, ROOT}

    public Employee() {
    }

    public Employee(String firstname, String lastname, EmployeeRole role, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    @Column(name = "firstname")
    @NotNull
    @Field
    @Analyzer(definition = "NameAnalyzer")
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @Column(name = "lastname")
    @NotNull
    @Field
    @Analyzer(definition = "NameAnalyzer")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Column(name = "role")
    @Field(analyze = Analyze.NO)
    @NotNull
    @Enumerated(EnumType.STRING)
    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
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
        if (firstname != null ? !firstname.equals(employee.firstname) : employee.firstname != null) return false;
        if (lastname != null ? !lastname.equals(employee.lastname) : employee.lastname != null) return false;
        if (role != employee.role) return false;
        if (username != null ? !username.equals(employee.username) : employee.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstname != null ? firstname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + employeeId;
        result = 31 * result + (address != null ? address.hashCode() : 0);
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

    @SuppressWarnings("MethodMayBeStatic")
    public String addBookEntities(Book book, int qty){
        if (role != EmployeeRole.ADMIN && role != EmployeeRole.ROOT) return "You don't have permission to add books.";
        for (int i = 0; i < qty; i++) {
            BookEntity newBE = new BookEntity(book, new Date());
            book.getBookEntities().add(newBE);
        }
        return "Added successfully";
    }

    @SuppressWarnings("MethodMayBeStatic")
    public String addItemEntities(Item item, int qty){
        if (role != EmployeeRole.ADMIN && role != EmployeeRole.ROOT) return "You don't have permission to add items.";
        for (int i = 0; i < qty; i++) {
            ItemEntity newBE = new ItemEntity(item, new Date());
            item.getItemEntities().add(newBE);
        }
        return "Added successfully";
    }

}