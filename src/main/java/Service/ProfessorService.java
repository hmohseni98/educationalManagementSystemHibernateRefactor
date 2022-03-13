package Service;

import Entity.Professor;
import Repository.ProfessorRepository;

public class ProfessorService extends PersonService<Professor, ProfessorRepository> {
    public ProfessorService() {
        super(new ProfessorRepository());
    }
}
