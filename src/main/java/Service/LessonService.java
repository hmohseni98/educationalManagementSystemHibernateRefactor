package Service;

import Entity.Lesson;
import Repository.LessonRepository;

public class LessonService extends BaseService<Lesson, LessonRepository> {

    public LessonService() {
        super(new LessonRepository());
    }
}
