package contacts.contact;

import java.util.Collection;

public interface Record {
    Collection<String> propertiesAsStrings();

    void updateProperty(String field, String value);

    String fullInfo();
}
