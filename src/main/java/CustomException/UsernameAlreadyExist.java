package CustomException;

public class UsernameAlreadyExist extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public UsernameAlreadyExist() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Username Already Exist" + TEXT_RESET;
    }
}
