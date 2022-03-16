package Repository;

import Database.SessionFactorySingleton;
import Entity.Professor;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorRepository implements ProfessorInterface {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Professor professor) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(professor);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Professor professor) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(professor);
                transaction.commit();
            } catch (Exception e) {
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
                String sql = "delete from professor where nationalcode=:id";
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
    public Professor findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Professor.class, id);
        }
    }

    @Override
    public List<Professor> findAll() {
        List<Professor> professorList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.Professor";
            var query = session.createQuery(hql, Professor.class);
            query.getResultStream().forEach(professorList::add);
            return professorList;
        }
    }

    @Override
    public Professor login(Integer nationalCode, String password) {
        Professor professor = null;
        try (var session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM professor WHERE nationalcode = :nationalCode and password = :password";
            var query = session.createNativeQuery(sql, Professor.class);
            query.setParameter("nationalCode", nationalCode);
            query.setParameter("password", password);
            professor = query.getSingleResult();
            return professor;
        }
    }

    @Override
    public Integer findLessonUnitById(Integer nationalCode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select sum(unit) from professor " +
                    "inner join presentinglesson p on professor.nationalcode = p.professor_nationalcode " +
                    "inner join lesson l on l.id = p.lesson_id " +
                    "where nationalcode = :nationalcode and year = :year and term = :term ";
            try {
                var query = session.createNativeQuery(sql);
                query.setParameter("nationalcode", nationalCode);
                query.setParameter("year", year);
                query.setParameter("term", term);
                if (query.getSingleResult() == null) {
                    return null;
                } else
                    return ((Number) query.getSingleResult()).intValue();
            } catch (NoResultException e) {
                return null;
            }
        }
    }
}
