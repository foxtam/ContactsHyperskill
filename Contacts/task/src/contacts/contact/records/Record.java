package contacts.contact.records;

import java.util.List;

public interface Record {
    List<String> propertiesAsStrings();

    String retrieveProperty(String field);

    void updateProperty(String field, String value);

    String fullInfo();
}
