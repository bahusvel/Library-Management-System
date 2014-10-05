package managers.search.entities;

import managers.search.SearchBase;
import persistance.Employee;

/**
 * Created by denislavrov on 9/26/14.
 */
public class EmployeeSearch extends SearchBase<Employee> {
    private static final Class<Employee> ENTITY = Employee.class;

    public EmployeeSearch(String input) {
        super(ENTITY, input);
        initFaceting();
        addFacetingRequest(buildDiscreteFacet("Role", "role"));
    }
}
