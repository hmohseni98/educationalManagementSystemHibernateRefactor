package CustomException;

public class RecordDoesNotExist extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public RecordDoesNotExist() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Record Does Not Exist" + TEXT_RESET;
    }
}
