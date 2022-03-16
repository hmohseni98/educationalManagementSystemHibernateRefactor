import CustomException.*;
import Database.FakeDB;
import Entity.*;
import Service.*;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    private static final Scanner scanner = new Scanner(System.in);
    private static Integer selectOption;
    private static String typeOfUser;
    private static final EmployeeService employeeService = new EmployeeService();
    private static final ProfessorService professorService = new ProfessorService();
    private static final StudentService studentService = new StudentService();
    private static final LessonService lessonService = new LessonService();
    private static final SelectUnitService selectUnitService = new SelectUnitService();
    private static final PresentingLessonService presentingLessonService = new PresentingLessonService();
    private static final LessonScoresService lessonScoresService = new LessonScoresService();
    private static Employee employee;
    private static Professor professor;
    private static Student student;
    private static Lesson lesson;
    private static SelectUnit selectUnit;
    private static LocalDateTime now = LocalDateTime.now();
    private static final FakeDB fakeDB = new FakeDB();

    public static void main(String[] args) {
        fakeDB.fillDB();
        welcomeMenu();
    }

    private static void welcomeMenu() {
        try {
            System.out.println("1.sign-in");
            System.out.println("2.sign-up");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                signInMenu();
            } else if (selectOption == 2) {
                signupMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidOption e) {
            System.out.println(e);
            welcomeMenu();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "invalid character" + TEXT_RESET);
            scanner.nextLine();
            welcomeMenu();
        }
    }

    private static void signupMenu() {
        try {
            System.out.print("select type of user(employee, professor, student):");
            typeOfUser = scanner.next();
            if (typeOfUser.equals("employee") || typeOfUser.equals("professor") || typeOfUser.equals("student")) {
                register(typeOfUser, "signup");
            } else {
                throw new InvalidTypeOfUser();
            }
        } catch (InvalidTypeOfUser e) {
            System.out.println(e);
            welcomeMenu();
        }
    }

    private static void register(String typeOfUser, String inputMenu) {
        try {
            System.out.print("national code:");
            String nationalCode = scanner.next();
            checkNationalCode(nationalCode);
            scanner.nextLine();
            System.out.print("first name:");
            String firstName = scanner.next();
            scanner.nextLine();
            System.out.print("last name:");
            String lastName = scanner.next();
            scanner.nextLine();
            System.out.print("address:");
            String address = scanner.next();
            scanner.nextLine();
            System.out.print("password:");
            String password = scanner.next();
            scanner.nextLine();
            if (typeOfUser.equals("employee")) {
                employee = new Employee(Integer.valueOf(nationalCode), firstName, lastName, address, password, 10000);
                employeeService.save(employee);
                System.out.println("register success!");
            } else if (typeOfUser.equals("professor")) {
                professor = new Professor(Integer.valueOf(nationalCode), firstName, lastName, address, password, TypeOfEmployment.tuition, 20000);
                professorService.save(professor);
                System.out.println("register success!");
            } else if (typeOfUser.equals("student")) {
                student = new Student(Integer.valueOf(nationalCode), firstName, lastName, address, password);
                studentService.save(student);
                System.out.println("register success!");
            }
            if (inputMenu.equals("signup"))
                welcomeMenu();
            else if (inputMenu.equals("employee"))
                employeeMainMenu();
        } catch (InvalidNationalCodeLength | InvalidNationalCodeCharacter e) {
            System.out.println(e);
            if (inputMenu.equals("signup"))
                welcomeMenu();
            else if (inputMenu.equals("employee"))
                employeeMainMenu();
        } catch (PersistenceException e) {
            System.out.println(TEXT_RED + "record does not exist!" + TEXT_RESET);
            if (inputMenu.equals("signup"))
                welcomeMenu();
            else if (inputMenu.equals("employee"))
                employeeMainMenu();
        }
    }

    private static void signInMenu() {
        try {
            System.out.print("select type of user(employee, professor, student):");
            typeOfUser = scanner.next();
            if (typeOfUser.equals("employee") || typeOfUser.equals("professor") || typeOfUser.equals("student")) {
                System.out.print("national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                scanner.nextLine();
                System.out.print("password:");
                String password = scanner.next();
                if (typeOfUser.equals("employee")) {
                    employee = employeeService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign-in success!");
                    employeeMainMenu();
                } else if (typeOfUser.equals("professor")) {
                    professor = professorService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign-in success!");
                    professorMainMenu();
                } else if (typeOfUser.equals("student")) {
                    student = studentService.login(Integer.valueOf(nationalCode), password);
                    System.out.println("sign-in success!");
                    studentMainMenu();
                }
            } else {
                throw new InvalidTypeOfUser();
            }
        } catch (NoResultException e) {
            System.out.println(TEXT_RED + "no record found in database!" + TEXT_RESET);
            welcomeMenu();
        } catch (InvalidTypeOfUser | InvalidNationalCodeLength | InvalidNationalCodeCharacter e) {
            System.out.println(e);
            welcomeMenu();
        }
    }

    private static void checkNationalCode(String nationalCode) {
        char[] array = nationalCode.toCharArray();
        for (char a : array) {
            if (!Character.isDigit(a))
                throw new InvalidNationalCodeCharacter();
        }
        if (nationalCode.length() != 10)
            throw new InvalidNationalCodeLength();
    }

    private static void studentMainMenu() {
        try {
            System.out.println("1.show information account");
            System.out.println("2.show list of presenting lesson");
            System.out.println("3.select unit");
            System.out.println("4.show list of passed lesson");
            System.out.println("5.back to welcome menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.println(student);
                studentMainMenu();
            } else if (selectOption == 2) {
                presentingLessonService.findAllByTerm(now.getYear(), calcTerm()).forEach(System.out::println);
                studentMainMenu();
            } else if (selectOption == 3) {
                System.out.print("please input presenting id:");
                Integer presentingId = scanner.nextInt();
                selectUnit = new SelectUnit(now.getYear(), calcTerm(), student, presentingLessonService.findById(presentingId));
                selectUnitService.save(selectUnit);
                System.out.println("added success");
                studentMainMenu();
            } else if (selectOption == 4) {
                lessonScoresService.passedLesson(student.getNationalCode()).forEach(System.out::println);
                studentMainMenu();
            } else if (selectOption == 5) {
                welcomeMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidOption | InputMismatchException | UnitSelectionCeiling e) {
            System.out.println(e);
            studentMainMenu();
        }
    }

    private static Integer calcTerm() {
        Integer currentMonth = now.getMonthValue();
        if (currentMonth <= 4)
            return 1;
        else if (currentMonth <= 9)
            return 2;
        else
            return 3;
    }

    private static void professorMainMenu() {
        try {
            System.out.println("1.show information account");
            System.out.println("2.show list of presenting lesson and student");
            System.out.println("3.input score for student");
            System.out.println("4.show salary information");
            System.out.println("5.back to welcome menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.println(professor);
                professorMainMenu();
            } else if (selectOption == 2) {
                System.out.print("please input lesson name:");
                String lName = scanner.next();
                lessonService.findByName(lName);
                selectUnitService.findAllByLessonNameAndProfessorId
                        (professor.getNationalCode(),lName,now.getYear(),calcTerm())
                        .forEach(System.out::println);
                professorMainMenu();
            } else if (selectOption == 3) {
                System.out.print("please enter student national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                System.out.print("lesson name:");
                String lessonName = scanner.next();
                System.out.print("score:");
                Float score = scanner.nextFloat();
                Boolean result = presentingLessonService.findLessonByProfessorId(now.getYear(), calcTerm(), professor.getNationalCode(), lessonName);
                if (result == true) {
                    LessonScores lessonScores = new LessonScores(now.getYear(), calcTerm(),
                            studentService.findById(Integer.valueOf(nationalCode)),
                            lessonService.findByName(lessonName), score);
                    lessonScoresService.save(lessonScores);
                    System.out.println("success!");
                    professorMainMenu();
                } else
                    throw new YouDidNotProvideThisLesson();
            } else if (selectOption == 4) {
                System.out.print("please input number of term:");
                Integer term = scanner.nextInt();
                if (term >= 1 && term <= 3) {
                    Integer totalOfUnit = professorService.findLessonUnitById(professor.getNationalCode(), now.getYear(), term);
                    Integer salary = professorService.calcSalary(professor, totalOfUnit, now.getYear(), term);
                    System.out.println(professor);
                    System.out.println("Total of unit: " + totalOfUnit);
                    System.out.println("Your salary: " + salary);
                    professorMainMenu();
                } else
                    throw new InvalidOption();
            } else if (selectOption == 5) {
                welcomeMenu();
            }
        } catch (InvalidOption | InputMismatchException | RecordDoesNotExist | YouDidNotProvideThisLesson e) {
            System.out.println(e);
            professorMainMenu();
        }
    }

    private static void employeeMainMenu() {
        try {
            System.out.println("1.student");
            System.out.println("2.professor");
            System.out.println("3.employee");
            System.out.println("4.lesson");
            System.out.println("5.show salary information");
            System.out.println("6.back to welcome menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                employeeToStudentMenu();
            } else if (selectOption == 2) {
                employeeToProfessorMenu();
            } else if (selectOption == 3) {
                employeeToEmployeeMenu();
            } else if (selectOption == 4) {
                employeeToCourseMenu();
            } else if (selectOption == 5) {
                employeeToShowAccount();
            } else if (selectOption == 6) {
                welcomeMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidOption | InputMismatchException e) {
            System.out.println(e);
            employeeMainMenu();
        }
    }

    private static void employeeToShowAccount() {
        System.out.println(employee);
        employeeMainMenu();
    }

    private static void employeeToCourseMenu() {
        try {
            System.out.println("1.add new lesson");
            System.out.println("2.update informational lesson");
            System.out.println("3.remove lesson");
            System.out.println("4.show all lessons");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                System.out.print("name:");
                String name = scanner.next();
                System.out.print("unit:");
                Integer unit = scanner.nextInt();
                lesson = new Lesson(name, unit);
                lessonService.save(lesson);
                System.out.println("add success");
                employeeToCourseMenu();
            } else if (selectOption == 2) {
                System.out.print("lesson id:");
                Integer id = scanner.nextInt();
                System.out.print("new name:");
                String name = scanner.next();
                System.out.print("new unit:");
                Integer unit = scanner.nextInt();
                Lesson lsnLoaded = lessonService.findById(id);
                lsnLoaded.setId(id);
                lsnLoaded.setName(name);
                lsnLoaded.setUnit(unit);
                lessonService.update(lsnLoaded);
                System.out.println("update success");
                employeeToCourseMenu();
            } else if (selectOption == 3) {
                System.out.print("lesson id:");
                Integer id = scanner.nextInt();
                lessonService.delete(id);
                System.out.println("delete success");
                employeeToCourseMenu();
            } else if (selectOption == 4) {
                lessonService.findAll().forEach(System.out::println);
                employeeToCourseMenu();
            } else if (selectOption == 5) {
                employeeMainMenu();
            } else
                throw new InvalidOption();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_RED + "invalid character" + TEXT_RESET);
            employeeMainMenu();
        } catch (InvalidOption | RecordDoesNotExist e) {
            System.out.println(e);
            employeeMainMenu();
        }
    }

    private static void employeeToEmployeeMenu() {
        try {
            System.out.println("1.add new employee");
            System.out.println("2.update informational employee");
            System.out.println("3.remove employee");
            System.out.println("4.show all employees");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                register("employee", "employee");
            } else if (selectOption == 2) {
                System.out.print("employee national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                System.out.print("new address:");
                String address = scanner.next();
                scanner.nextLine();
                System.out.print("new password:");
                String password = scanner.next();
                scanner.nextLine();
                System.out.print("new income:");
                Integer income = scanner.nextInt();
                Employee empLoaded = employeeService.findById(Integer.valueOf(nationalCode));
                empLoaded.setAddress(address);
                empLoaded.setPassword(password);
                empLoaded.setIncome(income);
                employeeService.update(empLoaded);
                System.out.println("update success");
                employeeToEmployeeMenu();
            } else if (selectOption == 3) {
                System.out.print("employee national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                if (Integer.valueOf(nationalCode).equals(employee.getNationalCode())) {
                    throw new CanNotRemoveYourAccount();
                }
                employeeService.delete(Integer.valueOf(nationalCode));
                System.out.println("remove success!");
                employeeToEmployeeMenu();
            } else if (selectOption == 4) {
                employeeService.findAll().forEach(System.out::println);
                employeeToEmployeeMenu();
            } else if (selectOption == 5) {
                employeeMainMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidNationalCodeCharacter | InvalidOption | InvalidNationalCodeLength | RecordDoesNotExist | CanNotRemoveYourAccount e) {
            System.out.println(e);
            employeeToEmployeeMenu();
        } catch (NullPointerException e) {
            System.out.println(TEXT_RED + "record does not exist" + TEXT_RESET);
            employeeToEmployeeMenu();
        }
    }

    private static void employeeToProfessorMenu() {
        try {
            System.out.println("1.add new professor");
            System.out.println("2.update informational professor");
            System.out.println("3.remove professor");
            System.out.println("4.show all professor");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                register("professor", "employee");
            } else if (selectOption == 2) {
                System.out.print("professor national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                System.out.print("new address:");
                String address = scanner.next();
                scanner.nextLine();
                System.out.print("new password:");
                String password = scanner.next();
                scanner.nextLine();
                System.out.print("new income:");
                Integer income = scanner.nextInt();
                Professor prfLoaded = professorService.findById(Integer.valueOf(nationalCode));
                System.out.println("new type of employment");
                System.out.println("1.scienceCommittee");
                System.out.println("2.tuition");
                System.out.print("please select one option:");
                Integer typeOfEmployment = scanner.nextInt();
                if (typeOfEmployment == 1) {
                    prfLoaded.setTypeOfEmployment(TypeOfEmployment.scienceCommittee);
                } else if (typeOfEmployment == 2) {
                    prfLoaded.setTypeOfEmployment(TypeOfEmployment.tuition);
                } else
                    throw new InvalidOption();
                prfLoaded.setAddress(address);
                prfLoaded.setPassword(password);
                prfLoaded.setIncome(income);
                professorService.update(prfLoaded);
                System.out.println("update success");
                employeeToProfessorMenu();
            } else if (selectOption == 3) {
                System.out.print("professor national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                professorService.delete(Integer.valueOf(nationalCode));
                System.out.println("remove success!");
                employeeToProfessorMenu();
            } else if (selectOption == 4) {
                professorService.findAll().forEach(System.out::println);
                employeeToProfessorMenu();
            } else if (selectOption == 5) {
                employeeMainMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidNationalCodeCharacter | InvalidOption | InvalidNationalCodeLength | RecordDoesNotExist e) {
            System.out.println(e);
            employeeToProfessorMenu();
        } catch (NullPointerException e) {
            System.out.println(TEXT_RED + "record does not exist" + TEXT_RESET);
            employeeToProfessorMenu();
        }
    }

    private static void employeeToStudentMenu() {
        try {
            System.out.println("1.add new student");
            System.out.println("2.update informational student");
            System.out.println("3.remove student");
            System.out.println("4.show all students");
            System.out.println("5.back to main menu");
            System.out.print("please select one option:");
            selectOption = scanner.nextInt();
            if (selectOption == 1) {
                register("student", "employee");
            } else if (selectOption == 2) {
                System.out.print("student national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                System.out.print("new address:");
                String address = scanner.next();
                scanner.nextLine();
                System.out.print("new password:");
                String password = scanner.next();
                Student stdLoaded = studentService.findById(Integer.valueOf(nationalCode));
                stdLoaded.setAddress(address);
                stdLoaded.setPassword(password);
                studentService.update(stdLoaded);
                System.out.println("update success");
                employeeToStudentMenu();
            } else if (selectOption == 3) {
                System.out.print("student national code:");
                String nationalCode = scanner.next();
                checkNationalCode(nationalCode);
                studentService.delete(Integer.valueOf(nationalCode));
                System.out.println("remove success!");
                employeeToStudentMenu();
            } else if (selectOption == 4) {
                studentService.findAll().forEach(System.out::println);
                employeeToStudentMenu();
            } else if (selectOption == 5) {
                employeeMainMenu();
            } else
                throw new InvalidOption();
        } catch (InvalidNationalCodeCharacter | InvalidOption | InvalidNationalCodeLength | RecordDoesNotExist e) {
            System.out.println(e);
            employeeToStudentMenu();
        } catch (NullPointerException e) {
            System.out.println(TEXT_RED + "record does not exist" + TEXT_RESET);
            employeeToStudentMenu();
        }
    }
}
