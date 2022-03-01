package CustomException;

public class InvalidParameter extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    
    public InvalidParameter() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Parameter" + TEXT_RESET;
    }
}
