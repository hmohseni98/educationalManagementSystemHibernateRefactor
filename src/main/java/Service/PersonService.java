package Service;

import Repository.UserInterface;

public abstract class PersonService<E,R extends UserInterface<E>> extends BaseService<E,R>{

    private R r;

    public PersonService(R r) {
        super(r);
        this.r = r;
    }


    public E login(String username , String password){
        return r.login(username,password);
    }
}
