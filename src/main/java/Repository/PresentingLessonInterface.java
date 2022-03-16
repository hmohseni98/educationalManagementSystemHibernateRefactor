package Repository;

import Entity.PresentingLesson;

import java.util.List;

public interface PresentingLessonInterface extends BaseRepository<PresentingLesson>{

    public List<PresentingLesson> findAllByTerm(Integer year,Integer term);

    public Boolean findLessonByProfessorId(Integer year , Integer term ,Integer prfNationalCode , String lessonName);
}
