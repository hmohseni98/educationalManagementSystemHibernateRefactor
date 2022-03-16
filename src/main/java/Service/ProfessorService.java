package Service;

import Entity.Professor;
import Entity.TypeOfEmployment;
import Repository.ProfessorRepository;

public class ProfessorService extends PersonService<Professor, ProfessorRepository> {
    private ProfessorRepository professorRepository = new ProfessorRepository();

    public ProfessorService() {
        super(new ProfessorRepository());
    }

    public Integer findLessonUnitById(Integer nationalCode,Integer year,Integer term){
        return professorRepository.findLessonUnitById(nationalCode, year, term);
    }
    public Integer calcSalary(Professor professor,Integer totalOfUnit,Integer year , Integer term){
        if (professor.getTypeOfEmployment().equals(TypeOfEmployment.tuition)){
            return totalOfUnit * 1000000;
        } else
            return totalOfUnit * 1000000 + professor.getIncome();
    }
}
