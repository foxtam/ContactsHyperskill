package contacts.contact.properties;

public class Surname {
    private final String surname;

    public Surname(final String surname) {
        if (!surname.matches(".*\\w.*")) throw new IllegalArgumentException(surname);
        this.surname = surname;
    }

    @Override
    public String toString() {
        return surname;
    }
}
