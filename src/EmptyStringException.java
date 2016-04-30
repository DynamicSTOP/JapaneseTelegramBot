public class EmptyStringException extends Exception {
    @Override
    public String getMessage() {
        return "empty string";
    }
}
