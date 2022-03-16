package Database;


import Entity.*;
import Service.*;

import java.util.*;


public class FakeDB {
    private EmployeeService employeeService = new EmployeeService();
    private StudentService studentService = new StudentService();
    private ProfessorService professorService = new ProfessorService();
    private LessonService lessonService = new LessonService();
    private PresentingLessonService presentingLessonService = new PresentingLessonService();
    private SelectUnitService selectUnitService = new SelectUnitService();
    private LessonScoresService lessonScoresService = new LessonScoresService();

    public void fillDB() {

        List<Employee> employeeList = new ArrayList<>();
        Employee[] employees = {
                new Employee(Integer.valueOf("2123926600"), "hassan", "mohseni",
                        "tehran", "123456", 1000),
                new Employee(Integer.valueOf("2123926601"), "hassan1", "mohseni1",
                        "shiraz", "123456", 1000),
                new Employee(Integer.valueOf("2123926602"), "hassan2", "mohseni2",
                        "kerman", "123456", 1000)
        };
        Collections.addAll(employeeList, employees);
        employeeList.forEach(employeeService::save);


        List<Student> studentList = new ArrayList<>();
        Student[] students = {
                new Student(Integer.valueOf("2123926610"), "mahdi", "karimi",
                        "tehran", "123456"),
                new Student(Integer.valueOf("2123926611"), "mahdi1", "karimi1",
                        "shiraz", "123456"),
                new Student(Integer.valueOf("2123926612"), "mahdi2", "karimi2",
                        "kerman", "123456")
        };
        Collections.addAll(studentList, students);
        studentList.forEach(studentService::save);

        List<Professor> professorList = new ArrayList<>();
        Professor[] professors = {
                new Professor(Integer.valueOf("2123926620"), "reza", "ahmadi",
                        "tehran", "123456", TypeOfEmployment.tuition, 2500),
                new Professor(Integer.valueOf("2123926621"), "reza1", "ahmadi1",
                        "shiraz", "123456", TypeOfEmployment.scienceCommittee, 3600),
                new Professor(Integer.valueOf("2123926622"), "reza2", "ahmadi2",
                        "kerman", "123456", TypeOfEmployment.tuition, 2700)
        };
        Collections.addAll(professorList, professors);
        professorList.forEach(professorService::save);

        List<Lesson> lessonList = new ArrayList<>();
        Lesson[] lessons = {
                new Lesson("math1", 3),
                new Lesson("shimi", 3),
                new Lesson("dini", 2),
                new Lesson("olum", 3),
                new Lesson("zist", 3),
                new Lesson("math2", 3),
                new Lesson("network", 3),
                new Lesson("network", 3),
                new Lesson("programming", 3)
        };
        Collections.addAll(lessonList, lessons);
        lessonList.forEach(lessonService::save);

        List<PresentingLesson> presentingLessonList = new ArrayList<>();
        PresentingLesson[] presentingLessons = {
                new PresentingLesson(2022,1,lessons[0], professors[0] ),
                new PresentingLesson(2022,1,lessons[1], professors[0] ),
                new PresentingLesson(2022,1,lessons[2], professors[0] ),
                new PresentingLesson(2022,1,lessons[3], professors[0] ),
                new PresentingLesson(2022,1,lessons[4], professors[1] ),
                new PresentingLesson(2022,1,lessons[5], professors[1] ),
                new PresentingLesson(2022,1,lessons[6], professors[1] ),
                new PresentingLesson(2022,1,lessons[7], professors[1] ),
        };
        Collections.addAll(presentingLessonList,presentingLessons);
        presentingLessonList.forEach(presentingLessonService::save);


        List<SelectUnit> selectUnitList = new ArrayList<>();
        SelectUnit[] selectUnits = {
                new SelectUnit(2022,1,students[0],presentingLessons[0] ),
                new SelectUnit(2022,1,students[1],presentingLessons[0] ),
                new SelectUnit(2022,1,students[2],presentingLessons[0] ),
                new SelectUnit(2022,1,students[0],presentingLessons[1] ),
                new SelectUnit(2022,1,students[1],presentingLessons[1] ),

        };
        Collections.addAll(selectUnitList,selectUnits);
        selectUnitList.forEach(selectUnitService::save);

        List<LessonScores> lessonScoresList = new ArrayList<>();
        LessonScores[] lessonScores = {
                new LessonScores(2021,3,students[0],lessons[0],19 ),
                new LessonScores(2021,3,students[0],lessons[2],16 ),
                new LessonScores(2021,3,students[0],lessons[3],14 ),
                new LessonScores(2021,3,students[0],lessons[5],10 ),
        };
        Collections.addAll(lessonScoresList,lessonScores);
        lessonScoresList.forEach(lessonScoresService::save);
    }
}