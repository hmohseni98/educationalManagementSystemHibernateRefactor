package Repository;

import Database.SessionFactorySingleton;
import Entity.Lesson;
import Entity.LessonScores;
import Entity.Student;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LessonScoresRepositoryTest {

    private LessonScoresRepository lessonScoresRepository;
    private LessonScores lessonScores;
    private Student student;
    private Lesson lesson;
    private Lesson lesson1;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        lessonScoresRepository = new LessonScoresRepository();
        StudentRepository studentRepository = new StudentRepository();
        LessonRepository lessonRepository = new LessonRepository();
        student = new Student(2123926610, "hassan", "mohseni", "tehran", "12345");
        studentRepository.save(student);
        lesson = new Lesson("math", 3);
        lessonRepository.save(lesson);
        lesson1 = new Lesson("math2", 3);
        lessonRepository.save(lesson1);
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        lessonScores = new LessonScores(2123926610, 2022,
                1, student, lesson, 20);

        //act
        lessonScoresRepository.save(lessonScores);

        //assert
        LessonScores loadedScr = lessonScoresRepository.findById(lessonScores.getId());

        assertNotNull(loadedScr);
        assertEquals(lessonScores.getTerm(), loadedScr.getTerm());
        assertEquals(20, loadedScr.getScore());
    }

    @Test
    public void updateTest() {
        //arrange
        lessonScores = new LessonScores(2123926610, 2022,
                1, student, lesson, 20);
        lessonScoresRepository.save(lessonScores);

        //act
        lessonScores.setScore(15);
        lessonScoresRepository.update(lessonScores);

        //assert
        LessonScores loadedScr = lessonScoresRepository.findById(lessonScores.getId());

        assertNotNull(loadedScr);
        assertEquals(15, loadedScr.getScore());
    }

    @Test
    public void deleteTest() {
        //arrange
        lessonScores = new LessonScores(2123926610, 2022,
                1, student, lesson, 20);
        lessonScoresRepository.save(lessonScores);

        //act
        lessonScoresRepository.delete(lessonScores.getId());

        //assert
        LessonScores loadedScr = lessonScoresRepository.findById(lessonScores.getId());
        assertNull(loadedScr);
    }

    @Test
    public void findByIdTest() {
        //arrange
        lessonScores = new LessonScores(2123926610, 2022,
                1, student, lesson, 20);

        //act
        lessonScoresRepository.save(lessonScores);
        LessonScores loadedScr = lessonScoresRepository.findById(lessonScores.getId());
        //assert
        List<LessonScores> lessonScoresList = lessonScoresRepository.findAll();
        assertAll(
                () -> assertEquals(lessonScoresList.get(0).getId(), loadedScr.getId()),
                () -> assertEquals(lessonScoresList.get(0).getStudent().getNationalCode(), loadedScr.getStudent().getNationalCode()),
                () -> assertEquals(lessonScoresList.get(0).getScore(), loadedScr.getScore())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        lessonScores = new LessonScores(2123926610, 2022,
                1, student, lesson, 20);
        LessonScores lessonScores1 = new LessonScores(2123926610, 2022,
                1, student, lesson1, 20);


        //act
        lessonScoresRepository.save(lessonScores);
        lessonScoresRepository.save(lessonScores1);

        //assert
        assertEquals(2, lessonScoresRepository.findAll().size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from lessonscores",
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