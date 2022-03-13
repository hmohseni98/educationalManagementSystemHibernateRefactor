package Service;

import Entity.Student;
import Repository.StudentRepository;

public class StudentService extends PersonService<Student, StudentRepository> {
    public StudentService() {
        super(new StudentRepository());
    }
}
