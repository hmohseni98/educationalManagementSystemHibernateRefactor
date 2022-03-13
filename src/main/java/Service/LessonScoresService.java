package Service;

import Entity.LessonScores;
import Repository.LessonScoresRepository;

public class LessonScoresService extends BaseService<LessonScores, LessonScoresRepository> {
    public LessonScoresService() {
        super(new LessonScoresRepository());
    }
}
