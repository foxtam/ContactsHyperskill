package contacts.contact.properties;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class BirthDate {
    private final LocalDate birthDate;

    public BirthDate(final String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(birthDate);
        }
    }

    @Override
    public String toString() {
        return birthDate.toString();
    }
}
