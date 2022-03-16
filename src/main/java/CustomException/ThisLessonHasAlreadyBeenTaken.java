package CustomException;

public class ThisLessonHasAlreadyBeenTaken extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public ThisLessonHasAlreadyBeenTaken() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "This Lesson Has Already Been Taken" + TEXT_RESET;
    }
}
