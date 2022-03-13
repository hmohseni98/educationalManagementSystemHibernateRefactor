package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.LessonScores;
import Entity.PresentingLesson;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LessonScoresRepository implements BaseRepository<LessonScores>{

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(LessonScores lessonScores) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.save(lessonScores);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(LessonScores lessonScores) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.update(lessonScores);
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
                String sql = "delete from lessonscores where id=:id";
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
    public LessonScores findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(LessonScores.class, id);
        }
    }

    @Override
    public List findAll() {
        List<LessonScores> lessonScoresList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM entity.LessonScores";
            var query = session.createQuery(hql, LessonScores.class);
            query.getResultStream().forEach(lessonScoresList::add);
            return lessonScoresList;
        }
    }
}
