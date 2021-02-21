package contacts;

import contacts.contact.Contact;
import contacts.exceptions.WrongPhoneNumberFormatException;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook = new PhoneBook();
    private boolean continued = true;

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        while (continued) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            final String action = scanner.nextLine();
            performAction(action);
        }
    }

    private void performAction(String action) {
        switch (action) {
            case "add":
                actionAdd();
                break;
            case "remove":
                actionRemove();
                break;
            case "edit":
                actionEdit();
                break;
            case "count":
                actionCount();
                break;
            case "list":
                actionList();
                break;
            case "exit":
                continued = false;
                break;
            default:
                System.out.println("Unknown action!");
        }
    }

    private void actionAdd() {
        System.out.print("Enter the name: ");
        final String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        final String surname = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = scanner.nextLine();

        final Contact contact = new Contact(name, surname);

        updateContactNumber(contact, number);
        phoneBook.add(contact);
        System.out.println("The record added.");
    }

    private void actionRemove() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }
        printPhoneBook();
        Contact contact = selectRecord();
        phoneBook.remove(contact);
        System.out.println("The record removed!");
    }

    private void actionEdit() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }
        printPhoneBook();
        Contact contact = selectRecord();
        String field = selectField();
        updateRecord(contact, field);
        System.out.println("The record updated!");
    }

    private void actionCount() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.");
    }

    private void actionList() {
        printPhoneBook();
    }

    private void updateContactNumber(Contact contact, String newValue) {
        try {
            contact.setPhoneNumber(newValue);
        } catch (WrongPhoneNumberFormatException e) {
            System.out.println("Wrong number format!");
        }
    }

    private void printPhoneBook() {
        List<Contact> contacts = phoneBook.asList();
        for (int i = 0; i < contacts.size(); i++) {
            final Contact contact = contacts.get(i);
            System.out.printf(
                    "%d. %s %s, %s%n",
                    i + 1,
                    contact.getName(),
                    contact.getSurname(),
                    contact.hasNumber() ? contact.getPhoneNumber() : "[no number]");
        }
    }

    private Contact selectRecord() {
        int index;
        do {
            System.out.print("Select a record: ");
            index = Integer.parseInt(scanner.nextLine());
        } while (index <= 0 || index > phoneBook.size());
        return phoneBook.asList().get(index - 1);
    }

    private String selectField() {
        String field;
        do {
            System.out.print("Select a field (name, surname, number): ");
            field = scanner.nextLine();
        } while (!List.of("name", "surname", "number").contains(field));
        return field;
    }

    private void updateRecord(Contact contact, String field) {
        String newValue = enterField(field);
        switch (field) {
            case "name":
                contact.setName(newValue);
                break;
            case "surname":
                contact.setSurname(newValue);
                break;
            case "number":
                updateContactNumber(contact, newValue);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    private String enterField(String field) {
        System.out.print("Enter " + field + ": ");
        return scanner.nextLine();
    }
}

