package Service;

import Entity.PresentingLesson;
import Repository.PresentingLessonRepository;

public class PresentingLessonService extends BaseService<PresentingLesson, PresentingLessonRepository> {
    public PresentingLessonService() {
        super(new PresentingLessonRepository());
    }
}
