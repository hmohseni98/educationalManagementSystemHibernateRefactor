package Repository;

import java.util.List;

public interface BaseRepository<T> {

    Integer save(T t);

    void update(T t);

    void delete(String nationalCode);

    T findById (String nationalCode);

    List<T> findAll ();
}
