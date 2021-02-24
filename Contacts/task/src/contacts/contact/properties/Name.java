package contacts.contact.properties;

public class Name {
    private final String name;

    public Name(final String name) {
        if (!name.matches("[a-zA-Z]+")) throw new IllegalArgumentException(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
