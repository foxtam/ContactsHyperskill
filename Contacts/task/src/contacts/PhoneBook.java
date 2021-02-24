package contacts;

import contacts.contact.Record;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Record> list = new ArrayList<>();

    public void add(Record contact) {
        list.add(contact);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return list.size();
    }

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

    public void updateRecord(int index, String field, String value) {
        list.get(index).updateProperty(field, value);
    }

    public Record get(int index) {
        return list.get(index);
    }
}
