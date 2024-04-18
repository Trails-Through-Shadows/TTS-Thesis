public class ValidationError extends RestSubError {

    private String message;
    private String object;
    private String field;
    private Object rejectedValue;

    public ValidationError(String object, String field, Object rejectedValue, String message, Object... args) {
        this.message = message.formatted(args);
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    @Override
    public String toString() {
        return object + "." + field + ": " + getMessage() + " (rejected value: '" + rejectedValue + "')";
    }
}