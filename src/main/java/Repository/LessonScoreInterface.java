package Repository;

import Entity.LessonScores;

import java.util.List;

public interface LessonScoreInterface extends BaseRepository<LessonScores>{

    public Double gradePointAverage(Integer nationalCode,Integer year,Integer term);

    public List<LessonScores> passedLesson(Integer nationalCode);

    public Boolean checkLessonPassed(Integer nationalCode, String lessonName);

    }
