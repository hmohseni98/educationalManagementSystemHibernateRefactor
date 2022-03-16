package Repository;

import Database.SessionFactorySingleton;
import Entity.*;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PresentingLessonRepositoryTest {

    private PresentingLessonRepository presentingLessonRepository;
    private PresentingLesson presentingLesson;
    private Professor professor;
    private Lesson lesson;
    private Lesson lesson1;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        presentingLessonRepository = new PresentingLessonRepository();
        sessionFactory = SessionFactorySingleton.getInstance();
        ProfessorRepository professorRepository = new ProfessorRepository();
        LessonRepository lessonRepository = new LessonRepository();
        professor = new Professor(2123926610,"hassan","mohseni","tehran"
                ,"12345",TypeOfEmployment.tuition,1000);
        professorRepository.save(professor);
        lesson = new Lesson("math",3);
        lessonRepository.save(lesson);
        lesson1 = new Lesson("math2",3);
        lessonRepository.save(lesson1);


    }

    @Test
    public void saveTest() {
        //arrange
        presentingLesson = new PresentingLesson(2022,3,lesson,professor);

        //act
        presentingLessonRepository.save(presentingLesson);

        //assert
        PresentingLesson loadedPzt = presentingLessonRepository.findById(presentingLesson.getId());

        assertNotNull(loadedPzt);
        assertEquals(presentingLesson.getTerm(), loadedPzt.getTerm());
        assertEquals(1000, loadedPzt.getProfessor().getIncome());
    }

    @Test
    public void updateTest() {
        //arrange
        presentingLesson = new PresentingLesson(2022,3,lesson,professor);
        presentingLessonRepository.save(presentingLesson);

        //act
        presentingLesson.setTerm(2);
        presentingLessonRepository.update(presentingLesson);

        //assert
        PresentingLesson loadedPzt = presentingLessonRepository.findById(presentingLesson.getId());

        assertNotNull(loadedPzt);
        assertEquals(2, loadedPzt.getTerm());
    }

    @Test
    public void deleteTest() {
        //arrange
        presentingLesson = new PresentingLesson(2022,3,lesson,professor);
        presentingLessonRepository.save(presentingLesson);

        //act
        presentingLessonRepository.delete(presentingLesson.getId());

        //assert
        PresentingLesson loadedPzt = presentingLessonRepository.findById(presentingLesson.getId());
        assertNull(loadedPzt);
    }

    @Test
    public void findByIdTest() {
        //arrange
        presentingLesson = new PresentingLesson(2022,3,lesson,professor);

        //act
        presentingLessonRepository.save(presentingLesson);
        PresentingLesson loadedPzt = presentingLessonRepository.findById(presentingLesson.getId());
        //assert
        List<PresentingLesson> presentingLessonList = presentingLessonRepository.findAll();
        assertAll(
                () -> assertEquals(presentingLessonList.get(0).getId(), loadedPzt.getId()),
                () -> assertEquals(presentingLessonList.get(0).getYear(), loadedPzt.getYear()),
                () -> assertEquals(presentingLessonList.get(0).getProfessor().getIncome(), loadedPzt.getProfessor().getIncome())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        presentingLesson = new PresentingLesson(2022,3,lesson,professor);
        PresentingLesson presentingLesson1 = new PresentingLesson(2022,3,lesson1,professor);


        //act
        presentingLessonRepository.save(presentingLesson);
        presentingLessonRepository.save(presentingLesson1);

        //assert
        assertEquals(2, presentingLessonRepository.findAll().size());
    }

    @AfterEach
    public void cleanUp() {
        String[] strings = new String[]{
                "delete from presentinglesson",
                "delete from professor",
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