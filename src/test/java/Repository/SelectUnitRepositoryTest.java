package Repository;

import Database.SessionFactorySingleton;
import Entity.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SelectUnitRepositoryTest {

    private SelectUnitRepository selectUnitRepository;
    private StudentRepository studentRepository;
    private PresentingLessonRepository presentingLessonRepository;
    private ProfessorRepository professorRepository;
    private LessonRepository lessonRepository;
    private Professor professor;
    private Lesson lesson;
    private Lesson lesson1;
    private SelectUnit selectUnit;
    private Student student;
    private PresentingLesson presentingLesson;
    private PresentingLesson presentingLesson1;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        selectUnitRepository = new SelectUnitRepository();
        studentRepository = new StudentRepository();
        presentingLessonRepository = new PresentingLessonRepository();
        professorRepository = new ProfessorRepository();
        lessonRepository = new LessonRepository();
        student = new Student(2123926610,"hassan","mohseni","tehran","12345");
        studentRepository.save(student);
        professor = new Professor(2123926610,"hassan","mohseni",
                "tehran","12345",TypeOfEmployment.tuition,5000);
        professorRepository.save(professor);
        lesson = new Lesson("math",3);
        lessonRepository.save(lesson);
        lesson1 = new Lesson("math2",3);
        lessonRepository.save(lesson1);
        presentingLesson = new PresentingLesson(2022,1,lesson,professor);
        presentingLessonRepository.save(presentingLesson);
        presentingLesson1 = new PresentingLesson(2022,1,lesson1,professor);
        presentingLessonRepository.save(presentingLesson1);
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        selectUnit = new SelectUnit(2022,1,student,presentingLesson);

        //act
        selectUnitRepository.save(selectUnit);

        //assert
        SelectUnit loadedSut = selectUnitRepository.findById(selectUnit.getId());

        assertNotNull(loadedSut);
        assertEquals(selectUnit.getYear(), loadedSut.getYear());
        assertEquals(1, loadedSut.getTerm());
    }

    @Test
    public void updateTest() {
        //arrange
        selectUnit = new SelectUnit(2022,1,student,presentingLesson);

        selectUnitRepository.save(selectUnit);

        //act
        selectUnit.setYear(2018);
        selectUnitRepository.update(selectUnit);

        //assert
        SelectUnit loadedSut = selectUnitRepository.findById(selectUnit.getId());

        assertNotNull(loadedSut);
        assertEquals(2018, loadedSut.getYear());
    }

    @Test
    public void deleteTest() {
        //arrange
        selectUnit = new SelectUnit(2022,1,student,presentingLesson);
        selectUnitRepository.save(selectUnit);


        //act
        selectUnitRepository.delete(selectUnit.getId());

        //assert
        SelectUnit loadedSut = selectUnitRepository.findById(selectUnit.getId());
        assertNull(loadedSut);
    }

    @Test
    public void findByIdTest() {
        //arrange
        selectUnit = new SelectUnit(2022,1,student,presentingLesson);

        //act
        selectUnitRepository.save(selectUnit);
        SelectUnit loadedSut = selectUnitRepository.findById(selectUnit.getId());
        //assert
        List<SelectUnit> employeeList = selectUnitRepository.findAll();
        assertAll(
                () -> assertEquals(employeeList.get(0).getId(), loadedSut.getId()),
                () -> assertEquals(employeeList.get(0).getTerm(), loadedSut.getTerm()),
                () -> assertEquals(employeeList.get(0).getStudent().getFirstName(), loadedSut.getStudent().getFirstName())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        selectUnit = new SelectUnit(2022,1,student,presentingLesson);
        SelectUnit selectUnit1 = new SelectUnit(2022,1,student,presentingLesson1);



        //act
        selectUnitRepository.save(selectUnit);
        selectUnitRepository.save(selectUnit1);

        //assert
        assertEquals(2, selectUnitRepository.findAll().size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from selectunit",
                "delete from lessonscores",
                "delete from presentinglesson",
                "delete from professor",
                "delete from student",
                "delete from lesson"
        };
        for (String s : strings) {
            try (var session = sessionFactory.openSession()) {
                var transaction = session.beginTransaction();
                try {
                    var query = session.createNativeQuery(s);
                    query.executeUpdate();
                    transaction.commit();
                } catch (Exception e) {
                    transaction.rollback();
                }
            }
        }
    }

    @AfterAll
    public static void afterAll() {

    }
}