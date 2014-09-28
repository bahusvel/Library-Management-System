package managers.search.entities;

import persistance.Employee;
import managers.search.SearchBase;

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
