package Service;

import CustomException.ThisLessonAlreadyPassed;
import CustomException.ThisLessonHasAlreadyBeenTaken;
import CustomException.UnitSelectionCeiling;
import Entity.SelectUnit;
import Repository.SelectUnitRepository;

import java.time.LocalDateTime;
import java.util.List;

public class SelectUnitService extends BaseService<SelectUnit, SelectUnitRepository> {

    private final SelectUnitRepository selectUnitRepository = new SelectUnitRepository();
    private final LessonScoresService lessonScoresService = new LessonScoresService();
    private final LocalDateTime now = LocalDateTime.now();
    private int currentYear;
    private int currentTerm;
    private int pastYear;
    private int pastTerm;


    public SelectUnitService() {
        super(new SelectUnitRepository());
    }

    @Override
    public void save(SelectUnit selectUnit) {
        Boolean result = lessonScoresService.checkLessonPassed(selectUnit.getStudent().getNationalCode(),
                selectUnit.getPresentingLesson().getLesson().getName());
        if (result == true)
            throw new ThisLessonAlreadyPassed();
        Boolean result1 = checkLessonTaken(selectUnit.getStudent().getNationalCode(),
                selectUnit.getPresentingLesson().getLesson().getName(), selectUnit.getYear(), selectUnit.getTerm());
        if (result1 == true)
            throw new ThisLessonHasAlreadyBeenTaken();
        currentYear = selectUnit.getYear();
        currentTerm = selectUnit.getTerm();
        calcPastTerm();
        Double gradeAverage = lessonScoresService.gradePointAverage(selectUnit.getStudent().getNationalCode(), pastYear, pastTerm);
        Integer countOfUnit;
        if (gradeAverage >= 18.0) {
            countOfUnit = 24;
        } else
            countOfUnit = 20;
        Integer tedad = calcTotalUnit(selectUnit.getStudent().getNationalCode(), selectUnit.getYear(), selectUnit.getTerm());
        if (tedad <= countOfUnit) {
            selectUnitRepository.save(selectUnit);
        } else
            throw new UnitSelectionCeiling();
    }

    public Integer calcTotalUnit(Integer nationalCode, Integer year, Integer term) {
        Integer result = selectUnitRepository.calcTotalUnit(nationalCode, year, term);
        if (result == null)
            return 0;
        else
            return result;
    }

    public List<SelectUnit> findAllByLessonNameAndProfessorId(Integer prfNationalCode, String lessonName, Integer year, Integer term) {
        return selectUnitRepository.findAllByLessonNameAndProfessorId(prfNationalCode, lessonName, year, term);
    }

    public Boolean checkLessonTaken(Integer nationalCode, String lessonName, Integer year, Integer term) {
        return selectUnitRepository.checkLessonTaken(nationalCode, lessonName, year, term);
    }

    private void calcPastTerm() {
        if (currentTerm == 1) {
            pastYear = currentYear - 1;
            pastTerm = 3;
        } else {
            pastTerm = -1;
        }
    }
}
