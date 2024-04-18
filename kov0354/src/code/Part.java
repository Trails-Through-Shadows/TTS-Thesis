public class Part extends Validable {

    protected String tag;
    protected String title;
    protected List<HexDTO> hexes = new ArrayList<>();

    @Override
    public void validateInner(ValidationConfig validationConfig) {

        // part has to have a correct tag and title
        Title title = new Title(getTitle());
        validateChild(title, validationConfig);

        Tag tag = new Tag(getTag());
        validateChild(tag, validationConfig);

        // min 5 hexes
        if (getHexes().size() < validationConfig.getMinHexes()) {
            errors.add(new ValidationError(getValidableClass(), "hexes", getHexes().size(), "Part must have at least %d hexes!".formatted(hexGrid.getMinHexes())));
        }

        // must include center startingHex
        Optional<Hex> centerHex = getHexes().stream().filter(hex -> hex.getQ() == 0 && hex.getR() == 0 && hex.getS() == 0).findFirst();
        if (centerHex.isEmpty()) {
            errors.add(new ValidationError(getValidableClass(), "hexes", null, "Part must include center startingHex!"));
            return;
        }
        
        // all hexes must be connected
        Navigation navigation = new Navigation(this);

        for (Hex hex : getHexes()) {
            if (hex == centerHex.get())
                continue;

            if (navigation.getPath(centerHex.get(), hex) == null) {
                errors.add(new ValidationError(getValidableClass(), hex.getValidableValue(), null, "Part must be connected!"));
                break;
            }
        }
    }
}