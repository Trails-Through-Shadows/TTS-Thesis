public abstract class Validable {

    protected List<RestSubError> errors = new ArrayList<>();

    public Optional<RestError> validate(@Nullable ValidationConfig validationConfig) {

        log.trace("Validating {}", getValidableClass());

        errors = new ArrayList<>();
        validateInner(validationConfig);

        if (errors.isEmpty()) {
            return Optional.empty();
        }

        RestError error = new RestError(HttpStatus.NOT_ACCEPTABLE, "{} is not valid!", getValidableClass());

        for (var e : errors) {
            error.addSubError(e);
        }

        return Optional.of(error);
    }
    
    protected abstract void validateInner(@Nullable ValidationConfig validationConfig);
}