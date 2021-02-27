package contacts.phonebook.searchphonebook;

import contacts.contact.Record;
import contacts.phonebook.PhoneBook;

import java.util.List;
import java.util.regex.Pattern;

public interface SearchPhoneBook extends PhoneBook {
    static SearchPhoneBook of(PhoneBook phoneBook) {
        return new SimpleSearchPhoneBook(phoneBook);
    }

    List<Record> searchByRegex(Pattern pattern);
}
