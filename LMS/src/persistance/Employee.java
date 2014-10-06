package persistance;

import lombok.*;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import persistance.base.Lease;
import persistance.base.Return;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * Created by denislavrov on 9/2/14.
 */
@Data
@EqualsAndHashCode(exclude = {"leases", "returns"})
@Entity
@Indexed
public class Employee {
    @NotNull
    @Field
    @Analyzer(definition = "NameAnalyzer")
    private String firstname;
    @NotNull
    @Field
    @Analyzer(definition = "NameAnalyzer")
    private String lastname;
    @NotNull
    @Field
    private String username;
    @Field(analyze = Analyze.NO)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeRole role;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int employeeId;
    @Embedded
    private Address address;
    @NotNull
    private String password;
    @OneToMany(mappedBy = "employee")
    private Collection<Lease> leases;
    @OneToMany(mappedBy = "employee")
    private Collection<Return> returns;

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

    @SuppressWarnings("MethodMayBeStatic")
    public String addBookEntities(Book book, int qty){
        if (role != EmployeeRole.ADMIN && role != EmployeeRole.ROOT) return "You don't have permission to add books.";
        for (int i = 0; i < qty; i++) {
            BookEntity newBE = new BookEntity(book, new Date());
            book.getEntities().add(newBE);
        }
        return "Added successfully";
    }

    @SuppressWarnings("MethodMayBeStatic")
    public String addItemEntities(Item item, int qty){
        if (role != EmployeeRole.ADMIN && role != EmployeeRole.ROOT) return "You don't have permission to add items.";
        for (int i = 0; i < qty; i++) {
            ItemEntity newBE = new ItemEntity(item, new Date());
            item.getEntities().add(newBE);
        }
        return "Added successfully";
    }

}
