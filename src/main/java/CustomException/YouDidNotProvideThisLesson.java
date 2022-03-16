package CustomException;

public class YouDidNotProvideThisLesson extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public YouDidNotProvideThisLesson() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "You Did Not Provide This Lesson" + TEXT_RESET;
    }
}
