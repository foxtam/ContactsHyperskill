package contacts.phonebook;

import contacts.contact.Record;

import java.util.ArrayList;
import java.util.List;

public class SimplePhoneBook implements PhoneBook {
    private final List<Record> list = new ArrayList<>();


    @Override
    public void add(Record contact) {
        list.add(contact);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void removeAt(int index) {
        list.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            builder.append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        return builder.toString();
    }

    @Override
    public Record get(int index) {
        return list.get(index);
    }
}
