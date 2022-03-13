package Repository;

import CustomException.UsernameAlreadyExist;
import Database.MyConnection;
import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.Professor;
import Entity.TypeOfEmployment;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements UserInterface<Professor> {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Professor professor) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.save(professor);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Professor professor) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.update(professor);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void delete(String id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from professor where id=:id";
                var query = session.createNativeQuery(sql);
                query.setParameter("id", id);
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Professor findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Professor.class, id);
        }
    }

    @Override
    public List<Professor> findAll() {
        List<Professor> professorList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM entity.Professor";
            var query = session.createQuery(hql, Professor.class);
            query.getResultStream().forEach(professorList::add);
            return professorList;
        }
    }

    @Override
    public Professor login(String username, String password) {
        Professor professor = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM professor WHERE username = :username and password = :password";
            var query = session.createNativeQuery(sql, Professor.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            professor = query.getSingleResult();
            return professor;
        }
    }
}
