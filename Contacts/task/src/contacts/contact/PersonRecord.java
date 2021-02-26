package contacts.contact;

import contacts.contact.properties.BirthDate;
import contacts.contact.properties.Gender;
import contacts.contact.properties.Name;
import contacts.contact.properties.Surname;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Consumer;

public class PersonRecord extends AbstractRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    public PersonRecord(PersonContact contact) {
        super(contact,
                Map.of(
                        "name", (Consumer<String> & Serializable) s -> contact.setName(new Name(s)),
                        "surname", (Consumer<String> & Serializable) s -> contact.setSurname(new Surname(s)),
                        "birth", (Consumer<String> & Serializable) s -> contact.setBirthDate(new BirthDate(s)),
                        "gender", (Consumer<String> & Serializable) s -> contact.setGender(new Gender(s))));
    }
}
