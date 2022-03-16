package Service;

import CustomException.RecordDoesNotExist;
import Repository.BaseRepository;

import java.util.List;

public abstract class BaseService<E, R extends BaseRepository<E>> {

    private R r;

    public BaseService(R r) {
        this.r = r;
    }

    public void save(E e) {
        r.save(e);
    }

    public void update(E e) {
        r.update(e);
    }

    public void delete(Integer id) {
        if (r.delete(id) == 0)
            throw new RecordDoesNotExist();
    }

    public E findById(Integer id) {
        return r.findById(id);
    }

    public List<E> findAll() {
        return r.findAll();
    }
}
