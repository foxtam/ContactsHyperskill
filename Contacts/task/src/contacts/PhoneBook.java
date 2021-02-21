package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> list = new ArrayList<>();

    public PhoneBook() {
    }

    public void add(Contact contact) {
        list.add(contact);
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "list=" + list +
                '}';
    }
}
