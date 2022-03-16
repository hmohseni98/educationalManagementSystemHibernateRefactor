package Repository;

public interface UserInterface<T> extends BaseRepository<T>{

    T login(Integer nationalCode, String password);
}
