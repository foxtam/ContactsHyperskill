package contacts;

import contacts.contact.Contact;

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

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return list.size();
    }

    public List<Contact> asList() {
        return list;
    }

    public void remove(Contact contact) {
        list.remove(contact);
    }
}
