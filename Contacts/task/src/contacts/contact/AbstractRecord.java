package contacts.contact;

import contacts.contact.properties.PhoneNumber;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractRecord implements Record {
    private final Map<String, Consumer<String>> propertiesFunctions = new HashMap<>();
    private final Contact contact;

    public AbstractRecord(Contact contact, Map<String, Consumer<String>> functions) {
        this.contact = contact;
        propertiesFunctions.put("number", s -> contact.setPhoneNumber(new PhoneNumber(s)));
        propertiesFunctions.putAll(functions);
    }

    @Override
    public Collection<String> propertiesAsStrings() {
        return propertiesFunctions.keySet();
    }

    @Override
    public void updateProperty(String field, String value) {
        propertiesFunctions.get(field).accept(value);
    }

    @Override
    public String fullInfo() {
        return contact.fullInfo();
    }

    @Override
    public String toString() {
        return contact.toString();
    }
}