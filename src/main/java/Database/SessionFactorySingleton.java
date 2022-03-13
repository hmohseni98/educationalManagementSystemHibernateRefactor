package Database;

import Entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(Professor.class)
                    .addAnnotatedClass(Lesson.class)
                    .addAnnotatedClass(PresentingLesson.class)
                    .addAnnotatedClass(LessonScores.class)
                    .addAnnotatedClass(SelectUnit.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
