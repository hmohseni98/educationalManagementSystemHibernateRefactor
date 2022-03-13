package Service;

import Entity.Employee;
import Repository.EmployeeRepository;

public class EmployeeService extends PersonService<Employee, EmployeeRepository> {

    public EmployeeService() {
        super(new EmployeeRepository());
    }
}
