package Service;

import Entity.SelectUnit;
import Repository.SelectUnitRepository;

public class SelectUnitService extends BaseService<SelectUnit, SelectUnitRepository> {
    public SelectUnitService() {
        super(new SelectUnitRepository());
    }
}
