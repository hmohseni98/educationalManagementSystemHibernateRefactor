package Service;

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

    public void delete(String id) {
        r.delete(id);
    }

    public E findById(String id) {
        return r.findById(id);
    }

    public List<E> findAll() {
        return r.findAll();
    }
}
