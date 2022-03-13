package Repository;

import java.util.List;

public interface BaseRepository<T> {

    void save(T t);

    void update(T t);

    void delete(String id);

    T findById(String id);

    List<T> findAll();
}
