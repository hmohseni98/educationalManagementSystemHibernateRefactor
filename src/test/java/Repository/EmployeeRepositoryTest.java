package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;
    private Employee employee;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        employeeRepository = new EmployeeRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);

        //act
        employeeRepository.save(employee);

        //assert
        Employee loadedEmp = employeeRepository.findById(employee.getNationalCode());

        assertNotNull(loadedEmp);
        assertEquals(employee.getFirstName(), loadedEmp.getFirstName());
        assertEquals(1000, loadedEmp.getIncome());
    }

    @Test
    public void updateTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);
        employeeRepository.save(employee);

        //act
        employee.setIncome(5000);
        employeeRepository.update(employee);

        //assert
        Employee loadedEmp = employeeRepository.findById(employee.getNationalCode());

        assertNotNull(loadedEmp);
        assertEquals(5000, loadedEmp.getIncome());
    }

    @Test
    public void deleteTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);
        employeeRepository.save(employee);

        //act
        employeeRepository.delete(employee.getNationalCode());

        //assert
        Employee loadedEmp = employeeRepository.findById(employee.getNationalCode());
        assertNull(loadedEmp);
    }

    @Test
    public void findByIdTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);

        //act
        employeeRepository.save(employee);
        Employee loadedEmp = employeeRepository.findById(employee.getNationalCode());
        //assert
        List<Employee> employeeList = employeeRepository.findAll();
        assertAll(
                () -> assertEquals(employeeList.get(0).getNationalCode(), loadedEmp.getNationalCode()),
                () -> assertEquals(employeeList.get(0).getIncome(), loadedEmp.getIncome()),
                () -> assertEquals(employeeList.get(0).getLastName(), loadedEmp.getLastName())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);
        Employee employee1 = new Employee(2123926611, "hassan1",
                "mohseni2", "tehran", "12346", 500);


        //act
        employeeRepository.save(employee);
        employeeRepository.save(employee1);

        //assert
        assertEquals(2, employeeRepository.findAll().size());
    }

    @Test
    public void loginTest() {
        //arrange
        employee = new Employee(2123926610, "hassan",
                "mohseni", "tehran", "123456", 1000);


        //act
        employeeRepository.save(employee);


        //assert
        Employee loadedEmp = employeeRepository.findById(employee.getNationalCode());

        assertAll(
                () -> assertEquals("hassan", loadedEmp.getFirstName()),
                () -> assertEquals("mohseni", loadedEmp.getLastName())
        );
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from employee");
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @AfterAll
    public static void afterAll() {

    }
}