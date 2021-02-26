package contacts.factory;

import contacts.phonebook.*;

import java.io.File;

public class PhoneBookFactory {
    private File file;

    public PhoneBookFactory() {
    }

    public PhoneBookFactory(File file) {
        this.file = file;
    }

    public PhoneBook createPhoneBook() {
        if (file == null) {
            return new SimplePhoneBook();
        } else {
            return new FilePhoneBook(file);
        }
    }
}
