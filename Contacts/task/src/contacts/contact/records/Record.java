package contacts.contact.records;

import java.util.Collection;

public interface Record {
    Collection<String> propertiesAsStrings();

    String retrieveProperty(String field);

    void updateProperty(String field, String value);

    String fullInfo();
}
