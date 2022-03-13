package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.Lesson;
import Entity.LessonScores;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements BaseRepository<Lesson> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(Lesson lesson) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(lesson);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(Lesson lesson) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(lesson);
                transaction.commit();
            } catch (Exception e) {
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
                String sql = "delete from lesson where id=:id";
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
    public Lesson findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Lesson.class, id);
        }
    }

    @Override
    public List findAll() {
        List<Lesson> lessonList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM entity.Lesson";
            var query = session.createQuery(hql, Lesson.class);
            query.getResultStream().forEach(lessonList::add);
            return lessonList;
        }
    }
}
