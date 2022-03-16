package CustomException;

public class CanNotRemoveYourAccount extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public CanNotRemoveYourAccount() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Can Not Remove Your Account" + TEXT_RESET;
    }
}
