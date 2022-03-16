package Repository;

import Database.SessionFactorySingleton;
import Entity.Employee;
import Entity.Professor;
import Entity.TypeOfEmployment;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorRepositoryTest {

    private ProfessorRepository professorRepository;
    private Professor professor;
    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }

    @BeforeEach
    public void beforeEach() {
        professorRepository = new ProfessorRepository();
        sessionFactory = SessionFactorySingleton.getInstance();


    }

    @Test
    public void saveTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);

        //act
        professorRepository.save(professor);

        //assert
        Professor loadedPrf = professorRepository.findById(professor.getNationalCode());

        assertNotNull(loadedPrf);
        assertEquals(professor.getFirstName(), loadedPrf.getFirstName());
        assertEquals(1000, loadedPrf.getIncome());
    }

    @Test
    public void updateTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);
        professorRepository.save(professor);

        //act
        professor.setIncome(5000);
        professorRepository.update(professor);

        //assert
        Professor loadedPrf = professorRepository.findById(professor.getNationalCode());

        assertNotNull(loadedPrf);
        assertEquals(5000, loadedPrf.getIncome());
    }

    @Test
    public void deleteTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);
        professorRepository.save(professor);


        //act
        professorRepository.delete(professor.getNationalCode());

        //assert
        Professor loadedPrf = professorRepository.findById(professor.getNationalCode());
        assertNull(loadedPrf);
    }

    @Test
    public void findByIdTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);

        //act
        professorRepository.save(professor);
        Professor loadedPrf = professorRepository.findById(professor.getNationalCode());
        //assert
        List<Professor> professorList = professorRepository.findAll();
        assertAll(
                () -> assertEquals(professorList.get(0).getNationalCode(), loadedPrf.getNationalCode()),
                () -> assertEquals(professorList.get(0).getIncome(), loadedPrf.getIncome()),
                () -> assertEquals(professorList.get(0).getLastName(), loadedPrf.getLastName())
        );
    }

    @Test
    public void findAllTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);
        Professor professor1 = new Professor(2123926611, "hassan1",
                "mohseni2", "tehran", "12346", TypeOfEmployment.scienceCommittee, 500);


        //act
        professorRepository.save(professor);
        professorRepository.save(professor1);

        //assert
        assertEquals(2, professorRepository.findAll().size());
    }

    @Test
    public void loginTest() {
        //arrange
        professor = new Professor(2123926610, "hassan",
                "mohseni", "tehran", "123456", TypeOfEmployment.tuition, 1000);


        //act
        professorRepository.save(professor);


        //assert
        Professor loadedPrf = professorRepository.findById(professor.getNationalCode());

        assertAll(
                () -> assertEquals("hassan", loadedPrf.getFirstName()),
                () -> assertEquals("mohseni", loadedPrf.getLastName())
        );
    }

    @AfterEach
    public void cleanUp() {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            try {
                var query = session.createNativeQuery("delete from professor");
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