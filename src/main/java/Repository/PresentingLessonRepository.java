package Repository;

import Entity.PresentingLesson;

import java.util.List;

public class PresentingLessonRepository implements BaseRepository<PresentingLesson> {
    @Override
    public Integer save(PresentingLesson presentingLesson) {
        return null;
    }

    @Override
    public void update(PresentingLesson presentingLesson) {

    }

    @Override
    public void delete(String nationalCode) {

    }

    @Override
    public PresentingLesson findById(String nationalCode) {
        return null;
    }

    @Override
    public List<PresentingLesson> findAll() {
        return null;
    }
}
