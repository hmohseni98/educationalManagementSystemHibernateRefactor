package Repository;

import Entity.SelectUnit;

import java.util.List;


public interface SelectUnitInterface extends BaseRepository<SelectUnit>{

    public Integer calcTotalUnit(Integer nationalCode, Integer year, Integer term);

    public List<SelectUnit> findAllByLessonNameAndProfessorId
            (Integer prfNationalCode , String lessonName,Integer year,Integer term );

    public Boolean checkLessonTaken(Integer nationalCode , String lessonName , Integer year , Integer term);
}
