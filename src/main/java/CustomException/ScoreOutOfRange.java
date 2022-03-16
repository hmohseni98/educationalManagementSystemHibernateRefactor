package CustomException;

public class ScoreOutOfRange extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public ScoreOutOfRange() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Score Out Of Range" + TEXT_RESET;
    }
}
