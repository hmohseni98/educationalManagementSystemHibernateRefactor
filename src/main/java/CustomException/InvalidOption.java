package CustomException;

public class InvalidOption extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidOption() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Option" + TEXT_RESET;
    }
}
