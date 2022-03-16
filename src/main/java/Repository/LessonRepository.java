package Repository;

import Database.SessionFactorySingleton;
import Entity.Lesson;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements LessonInterface {
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
    public int delete(Integer id) {
        int res = 0;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from lesson where id=:id";
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
    public Lesson findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(Lesson.class, id);
        }
    }

    @Override
    public List findAll() {
        List<Lesson> lessonList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.Lesson";
            var query = session.createQuery(hql, Lesson.class);
            query.getResultStream().forEach(lessonList::add);
            return lessonList;
        }
    }

    @Override
    public Lesson findByName(String name) {
        try(var session = sessionFactory.openSession()){
            String sql = "select * from lesson " +
                    "where name = :name";
            var query = session.createNativeQuery(sql,Lesson.class);
            query.setParameter("name",name);
            return query.getSingleResult();
        }
    }
}
