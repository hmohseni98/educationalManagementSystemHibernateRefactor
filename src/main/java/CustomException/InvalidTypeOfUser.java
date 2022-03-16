package CustomException;

public class InvalidTypeOfUser extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidTypeOfUser() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Type Of User" + TEXT_RESET;
    }
}
