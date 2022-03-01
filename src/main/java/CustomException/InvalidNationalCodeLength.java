package CustomException;

public class InvalidNationalCodeLength extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidNationalCodeLength() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid National Code Length" + TEXT_RESET;
    }
}
