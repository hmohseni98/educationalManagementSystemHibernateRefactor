package Repository;

import Database.SessionFactorySingleton;
import Entity.Student;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements UserInterface<Student> {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Student student) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Student student) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public int delete(Integer nationalCode) {
        int res = 0;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from student where nationalcode=:id";
                var query = session.createNativeQuery(sql);
                query.setParameter("id", nationalCode);
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
    public Student findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Student.class, id);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.Student";
            var query = session.createQuery(hql, Student.class);
            query.getResultStream().forEach(studentList::add);
            return studentList;
        }
    }

    @Override
    public Student login(Integer nationalCode, String password) {
        Student student = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM student WHERE nationalcode = :nationalCode and password = :password";
            var query = session.createNativeQuery(sql, Student.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("password", password);
            student = query.getSingleResult();
            return student;
        }
    }
}
