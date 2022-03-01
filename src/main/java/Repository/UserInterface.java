package Repository;

public interface UserInterface<T> extends BaseRepository<T>{

    Boolean login(T t);
}
