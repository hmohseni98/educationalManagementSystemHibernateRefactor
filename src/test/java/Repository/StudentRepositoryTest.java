package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.Professor;
import Entity.Student;
import Entity.TypeOfEmployment;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    private StudentRepository studentRepository;
    private Student student;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        studentRepository = new StudentRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");

        //act
        studentRepository.save(student);

        //assert
        Student loadedStd = studentRepository.findById(student.getNationalCode());

        assertNotNull(loadedStd);
        assertEquals("hassan", loadedStd.getFirstName());
        assertEquals("tehran", loadedStd.getAddress());
    }

    @Test
    public void updateTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");
        studentRepository.save(student);

        //act
        student.setLastName("karami");
        studentRepository.update(student);

        //assert
        Student loadedStd = studentRepository.findById(student.getNationalCode());

        assertNotNull(loadedStd);
        assertEquals("karami", loadedStd.getLastName());
    }

    @Test
    public void deleteTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");
        studentRepository.save(student);

        //act
        studentRepository.delete(student.getNationalCode());

        //assert
        Student loadedStd = studentRepository.findById(student.getNationalCode());
        assertNull(loadedStd);
    }

    @Test
    public void findByIdTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");

        //act
        studentRepository.save(student);
        Student loadedStd = studentRepository.findById(student.getNationalCode());
        //assert
        List<Student> studentList = studentRepository.findAll();
        assertAll(
                () -> assertEquals(studentList.get(0).getNationalCode(), loadedStd.getNationalCode()),
                () -> assertEquals(studentList.get(0).getFirstName(), loadedStd.getFirstName()),
                () -> assertEquals(studentList.get(0).getLastName(), loadedStd.getLastName())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");
        Student student1 = new Student(2123926611, "hassan2",
                "mohseni2", "tehran", "1234");


        //act
        studentRepository.save(student);
        studentRepository.save(student1);

        //assert
        assertEquals(2, studentRepository.findAll().size());
    }

    @Test
    public void loginTest() {
        //arrange
        student = new Student(2123926610, "hassan",
                "mohseni", "tehran", "123456");


        //act
        studentRepository.save(student);


        //assert
        Student loadedSd = studentRepository.findById(student.getNationalCode());

        assertAll(
                () -> assertEquals("hassan", loadedSd.getFirstName()),
                () -> assertEquals("mohseni", loadedSd.getLastName())
        );
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from student");
                query.executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }
        }
    }

    @AfterAll
    public static void afterAll() {

    }
}