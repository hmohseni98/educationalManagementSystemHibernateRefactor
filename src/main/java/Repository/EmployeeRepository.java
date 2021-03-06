package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository implements UserInterface<Employee> {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Employee employee) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(employee);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Employee employee) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.update(employee);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public int delete(Integer id) {
        int res = 0;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from employee where nationalcode=:id";
                var query = session.createNativeQuery(sql);
                query.setParameter("id", id);
                res = query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
        return res;
    }

    @Override
    public Employee findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Employee.class, id);
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.Employee";
            var query = session.createQuery(hql, Employee.class);
            query.getResultStream().forEach(employeeList::add);
            return employeeList;
        }
    }

    @Override
    public Employee login(Integer nationalCode, String password) {
        Employee employee = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM employee WHERE nationalcode = :nationalCode and password = :password";
            var query = session.createNativeQuery(sql, Employee.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("password", password);
            employee = query.getSingleResult();
            return employee;
        }
    }
}
