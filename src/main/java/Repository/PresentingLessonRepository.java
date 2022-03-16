package Repository;

import Database.SessionFactorySingleton;
import Entity.PresentingLesson;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class PresentingLessonRepository implements PresentingLessonInterface {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(PresentingLesson presentingLesson) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(presentingLesson);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(PresentingLesson presentingLesson) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(presentingLesson);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public int delete(Integer id) {
        int res;
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from presentinglesson where id=:id";
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
    public PresentingLesson findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(PresentingLesson.class, id);
        }
    }

    @Override
    public List<PresentingLesson> findAll() {
        List<PresentingLesson> presentingLessonList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.PresentingLesson";
            var query = session.createQuery(hql, PresentingLesson.class);
            query.getResultStream().forEach(presentingLessonList::add);
            return presentingLessonList;
        }
    }

    @Override
    public List<PresentingLesson> findAllByTerm(Integer year, Integer term) {
        List<PresentingLesson> presentingLessonList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from presentinglesson " +
                    "where year = :year and term = :term ";
            var query = session.createNativeQuery(sql, PresentingLesson.class);
            query.setParameter("year", year);
            query.setParameter("term", term);
            query.getResultStream().forEach(presentingLessonList::add);
            return presentingLessonList;
        }
    }

    @Override
    public Boolean findLessonByProfessorId(Integer year, Integer term, Integer prfNationalCode, String lessonName) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select * from presentinglesson " +
                    "inner join lesson l on l.id = presentinglesson.lesson_id " +
                    "inner join professor p on p.nationalcode = presentinglesson.professor_nationalcode " +
                    "where year = :year and term = :term and p.nationalcode = :prfnationalcode  and l.name = :lessonname ";
            try {
                var query = session.createNativeQuery(sql, PresentingLesson.class);
                query.setParameter("year", year);
                query.setParameter("term", term);
                query.setParameter("prfnationalcode", prfNationalCode);
                query.setParameter("lessonname", lessonName);
                PresentingLesson result = query.getSingleResult();
                if (result == null)
                    return false;
                else
                    return true;
            } catch (NoResultException e) {
                return false;
            }
        }
    }
}
