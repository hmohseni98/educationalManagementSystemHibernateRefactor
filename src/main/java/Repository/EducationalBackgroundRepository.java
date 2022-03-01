package Repository;

import Entity.EducationalBackground;

import java.util.List;

public class EducationalBackgroundRepository implements BaseRepository<EducationalBackground>{
    @Override
    public Integer save(EducationalBackground educationalBackground) {
        return null;
    }

    @Override
    public void update(EducationalBackground educationalBackground) {

    }

    @Override
    public void delete(String nationalCode) {

    }

    @Override
    public EducationalBackground findById(String nationalCode) {
        return null;
    }

    @Override
    public List<EducationalBackground> findAll() {
        return null;
    }
}
