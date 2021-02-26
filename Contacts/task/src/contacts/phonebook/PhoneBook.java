package contacts.phonebook;

import contacts.contact.Record;

public interface PhoneBook {
    void add(Record record);
    void removeAt(int index);
    Record get(int index);
    int size();
    boolean isEmpty();
}
