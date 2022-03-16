package CustomException;

public class ThisLessonAlreadyPassed extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public ThisLessonAlreadyPassed() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "This Lesson Already Passed" + TEXT_RESET;
    }
}
