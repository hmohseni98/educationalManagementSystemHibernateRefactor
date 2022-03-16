package Repository;

import Database.SessionFactorySingleton;

import Entity.SelectUnit;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

public class SelectUnitRepository implements SelectUnitInterface {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void save(SelectUnit selectUnit) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.save(selectUnit);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void update(SelectUnit selectUnit) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                session.update(selectUnit);
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
                String sql = "delete from selectunit where id=:id";
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
    public SelectUnit findById(Integer id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(SelectUnit.class, id);
        }
    }

    @Override
    public List<SelectUnit> findAll() {
        List<SelectUnit> selectUnitList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM Entity.SelectUnit";
            var query = session.createQuery(hql, SelectUnit.class);
            query.getResultStream().forEach(selectUnitList::add);
            return selectUnitList;
        }
    }

    @Override
    public Integer calcTotalUnit(Integer nationalCode, Integer year, Integer term) {
        try (var session = sessionFactory.openSession()) {
            String sql = "select Sum(unit) from selectunit " +
                    "INNER JOIN presentinglesson p on p.id = selectunit.presentinglesson_id " +
                    "INNER JOIN lesson l on l.id = p.lesson_id " +
                    "where student_nationalcode = :nationalcode and selectunit.year = :year and selectunit.term = :term";
            var query = session.createNativeQuery(sql);
            query.setParameter("nationalcode", nationalCode);
            query.setParameter("year", year);
            query.setParameter("term", term);
            if (query.getSingleResult() == null){
                return null;
            } else
                return ((Number)query.getSingleResult()).intValue();
        }
    }

    @Override
    public List<SelectUnit> findAllByLessonNameAndProfessorId(Integer prfNationalCode, String lessonName, Integer year, Integer term) {
        List<SelectUnit> selectUnitList = new ArrayList<>();
        try (var session = sessionFactory.openSession()) {
            String sql = "select s.* from selectunit s " +
                    "inner join presentinglesson p on p.id = s.presentinglesson_id " +
                    "inner join lesson l on l.id = p.lesson_id " +
                    "where professor_nationalcode = :prfnationalcode and" +
                    " l.name = :lessonname and s.year = :year and s.term = :term ";
            var query = session.createNativeQuery(sql, SelectUnit.class);
            query.setParameter("prfnationalcode",prfNationalCode);
            query.setParameter("lessonname",lessonName);
            query.setParameter("year",year);
            query.setParameter("term",term);
            query.getResultStream().forEach(selectUnitList::add);
            return selectUnitList;
        }
    }
}
