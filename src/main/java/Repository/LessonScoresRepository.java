package Repository;

import Database.SessionFactorySingleton;
import Entity.LessonScores;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LessonScoresRepository implements LessonScoreInterface {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(LessonScores lessonScores) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(lessonScores);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(LessonScores lessonScores) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(lessonScores);
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
                String sql = "delete from lessonscores where id=:id";
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
    public LessonScores findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(LessonScores.class, id);
        }
    }

    @Override
    public List findAll() {
        List<LessonScores> lessonScoresList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.LessonScores";
            var query = session.createQuery(hql, LessonScores.class);
            query.getResultStream().forEach(lessonScoresList::add);
            return lessonScoresList;
        }
    }

    @Override
    public Double gradePointAverage(Integer nationalCode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select Avg(score) from lessonscores " +
                    "where student_nationalcode = :nationalcode and year = :year and term = :term ";
            var query = session.createNativeQuery(sql);
            query.setParameter("nationalcode", nationalCode);
            query.setParameter("year", year);
            query.setParameter("term", term);
            return (Double) query.getSingleResult();
        }
    }

    @Override
    public List<LessonScores> passedLesson(Integer nationalCode) {
        List<LessonScores> lessonScoresList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from lessonscores " +
                    "where student_nationalcode = :nationalcode and score >= 10 ";
            var query = session.createNativeQuery(sql,LessonScores.class);
            query.setParameter("nationalcode", nationalCode);
            query.getResultStream().forEach(lessonScoresList::add);
            return lessonScoresList;
        }
    }
}
