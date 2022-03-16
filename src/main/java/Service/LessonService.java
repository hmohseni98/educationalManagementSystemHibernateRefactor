package Service;

import CustomException.RecordDoesNotExist;
import Entity.Lesson;
import Repository.LessonRepository;

public class LessonService extends BaseService<Lesson, LessonRepository> {
    private LessonRepository lessonRepository = new LessonRepository();


    public LessonService() {
        super(new LessonRepository());
    }

    public Lesson findByName(String name) {
    Lesson lesson =  lessonRepository.findByName(name);
    if (lesson != null)
        return lesson;
    else
        throw new RecordDoesNotExist();
    }
}
