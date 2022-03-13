package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.PresentingLesson;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PresentingLessonRepository implements BaseRepository<PresentingLesson> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(PresentingLesson presentingLesson) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.save(presentingLesson);
                transaction.commit();
            } catch (Exception e){
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(PresentingLesson presentingLesson) {
        try(var session = sessionFactory.openSession() ){
            var transaction = session.beginTransaction();
            try{
                session.update(presentingLesson);
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
                String sql = "delete from presentinglesson where id=:id";
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
    public PresentingLesson findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(PresentingLesson.class, id);
        }
    }

    @Override
    public List<PresentingLesson> findAll() {
        List<PresentingLesson> presentingLessonList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM entity.PresentingLesson";
            var query = session.createQuery(hql, PresentingLesson.class);
            query.getResultStream().forEach(presentingLessonList::add);
            return presentingLessonList;
        }
    }
}
