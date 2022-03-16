package Service;

import CustomException.YouDidNotProvideThisLesson;
import Entity.PresentingLesson;
import Repository.PresentingLessonRepository;

import java.util.List;

public class PresentingLessonService extends BaseService<PresentingLesson, PresentingLessonRepository> {

    private PresentingLessonRepository presentingLessonRepository = new PresentingLessonRepository();

    public PresentingLessonService() {
        super(new PresentingLessonRepository());
    }

    public List<PresentingLesson> findAllByTerm(Integer year, Integer term) {
        return presentingLessonRepository.findAllByTerm(year, term);
    }

    public Boolean findLessonByProfessorId(Integer year, Integer term, Integer prfNationalCode, String lessonName) {
        Boolean result = presentingLessonRepository.findLessonByProfessorId(year, term, prfNationalCode, lessonName);
        if (result == false) {
            return false;
        } else
            return true;
    }
}
