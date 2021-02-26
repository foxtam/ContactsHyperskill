package contacts.phonebook;

import contacts.contact.Record;
import contacts.exceptions.LoadPhoneBookException;
import contacts.exceptions.UploadPhoneBookException;

import java.io.*;

public class FilePhoneBook implements PhoneBook {
    private final File file;
    private PhoneBook phoneBook;

    public FilePhoneBook(File file) {
        this.file = file;
        loadPhoneBook();
    }

    private void loadPhoneBook() {
        try {
            tryLoadPhoneBook();
        } catch (IOException | ClassNotFoundException e) {
            throw new LoadPhoneBookException(e);
        }
    }

    private void tryLoadPhoneBook() throws IOException, ClassNotFoundException {
        ObjectInputStream ois =
                new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(this.file)));
        try (ois) {
            phoneBook = (PhoneBook) ois.readObject();
        }
    }

    @Override
    public void add(Record contact) {
        phoneBook.add(contact);
        uploadPhoneBook();
    }

    private void uploadPhoneBook() {
        try {
            tryUploadPhoneBook();
        } catch (IOException e) {
            throw new UploadPhoneBookException(e);
        }
    }

    private void tryUploadPhoneBook() throws IOException {
        ObjectOutputStream oos =
                new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(this.file)));
        try (oos) {
            oos.writeObject(phoneBook);
        }
    }

    @Override
    public void removeAt(int index) {
        phoneBook.removeAt(index);
        uploadPhoneBook();
    }

    @Override
    public Record get(int index) {
        return phoneBook.get(index);
    }

    @Override
    public int size() {
        return phoneBook.size();
    }

    @Override
    public boolean isEmpty() {
        return phoneBook.isEmpty();
    }
}
