import Database.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class SessionFactoryTest {

    private SessionFactory sessionFactory;

    @BeforeAll
    public static void beforeAll() {
    }
    @BeforeEach
    public void beforeEach() {
    }

    @Test
    public void sessionFactoryOpenTest(){
        //arrange
        sessionFactory = SessionFactorySingleton.getInstance();

        //act
        var session = sessionFactory.openSession();

        //assert
        assertEquals(true,session.isOpen());
    }

    @Test
    public void sessionFactoryCloseTest(){
        //arrange
        sessionFactory = SessionFactorySingleton.getInstance();

        //act
        var session = sessionFactory.openSession();
        session.close();

        //assert
        assertEquals(false,session.isOpen());
    }

    @AfterEach
    public void afterEach(){
    }
    @AfterAll
    public static void afterAll(){
    }
}