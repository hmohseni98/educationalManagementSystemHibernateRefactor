package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.SelectUnit;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class SelectUnitRepository implements BaseRepository<SelectUnit> {
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
    public void delete(String id) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                String sql = "delete from selectunit where id=:id";
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
    public SelectUnit findById(String id) {
        try (var session = sessionFactory.openSession()) {
            return session.find(SelectUnit.class, id);
        }
    }

    @Override
    public List<SelectUnit> findAll() {
        List<SelectUnit> selectUnitList = new ArrayList<>();
        try(var session = sessionFactory.openSession()) {
            String hql = "FROM entity.SelectUnit";
            var query = session.createQuery(hql, SelectUnit.class);
            query.getResultStream().forEach(selectUnitList::add);
            return selectUnitList;
        }
    }
}
