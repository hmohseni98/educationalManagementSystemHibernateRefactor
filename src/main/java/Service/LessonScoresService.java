package Service;

import Entity.LessonScores;
import Repository.LessonScoresRepository;

import java.util.List;

public class LessonScoresService extends BaseService<LessonScores, LessonScoresRepository> {
    private final LessonScoresRepository lessonScoresRepository = new LessonScoresRepository();

    public LessonScoresService() {
        super(new LessonScoresRepository());
    }

    public Double gradePointAverage(Integer nationalCode, Integer year, Integer term){
        Double result =  lessonScoresRepository.gradePointAverage(nationalCode,year,term);
        if (result == null)
            return 17.0;
        else
            return result;
    }

    public List<LessonScores> passedLesson(Integer nationalCode) {
        return lessonScoresRepository.passedLesson(nationalCode);
    }

}
