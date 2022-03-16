package CustomException;

public class UnitSelectionCeiling extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public UnitSelectionCeiling() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Unit Selection Ceiling" + TEXT_RESET;
    }
}
