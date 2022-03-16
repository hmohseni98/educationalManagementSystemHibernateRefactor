package Repository;

import Entity.Professor;

public interface ProfessorInterface extends UserInterface<Professor> {

    public Integer findLessonUnitById (Integer nationalCode , Integer year, Integer term);
}
