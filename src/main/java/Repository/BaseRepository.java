package Repository;

import java.util.List;

public interface BaseRepository<T> {

    void save(T t);

    void update(T t);

    int delete(Integer id);

    T findById(Integer id);

    List<T> findAll();
}
