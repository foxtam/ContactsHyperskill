package contacts;

import contacts.contact.Record;
import contacts.contact.*;
import contacts.contact.properties.*;
import contacts.factory.FilePhoneBookFactory;
import contacts.factory.PhoneBookFactory;
import contacts.factory.SimplePhoneBookFactory;
import contacts.phonebook.PhoneBook;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook;
    private boolean continued = true;
    private final Map<String, Runnable> commands =
            Map.of(
                    "add", this::actionAdd,
                    "remove", this::actionRemove,
                    "edit", this::actionEdit,
                    "count", this::actionCount,
                    "info", this::actionInfo,
                    "exit", this::actionExit);
    private final String joinedCommands = String.join(", ", commands.keySet());

    public Main(PhoneBookFactory factory) {
        this.phoneBook = factory.createPhoneBook();
    }

    public static void main(String[] args) {
        Optional<String> fileNameOptional = new Args(args).get("open");
        PhoneBookFactory factory =
                fileNameOptional
                        .<PhoneBookFactory>map(s -> new FilePhoneBookFactory(new File(s)))
                        .orElseGet(SimplePhoneBookFactory::new);
        new Main(factory).run();
    }

    private void run() {
        while (continued) {
            System.out.print("Enter action (" + joinedCommands + "): ");
            String action = scanner.nextLine();
            performAction(action);
        }
    }

    private void performAction(String action) {
        Runnable command = commands.get(action);
        if (command == null) {
            System.out.println("Unknown action!\n");
        } else {
            command.run();
        }
    }

    private void actionExit() {
        continued = false;
    }

    private void actionAdd() {
        Record record = readRecord();
        phoneBook.add(record);
        System.out.println("The record added.\n");
    }

    private void actionRemove() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to remove!\n");
            return;
        }
        printPhoneBook();
        int contactIndex = readRecordIndex("Select a record: ");
        phoneBook.removeAt(contactIndex);
        System.out.println("The record removed!\n");
    }

    private void printPhoneBook() {
        System.out.print(phoneBook);
    }

    private int readRecordIndex(String message) {
        int index;
        do {
            System.out.print(message);
            index = Integer.parseInt(scanner.nextLine());
        } while (index <= 0 || index > phoneBook.size());
        return index - 1;
    }

    private void actionEdit() {
        if (phoneBook.isEmpty()) {
            System.out.println("No records to edit!\n");
            return;
        }
        printPhoneBook();
        int recordIndex = readRecordIndex("Select a record: ");
        Record record = phoneBook.get(recordIndex);
        String field = readFieldName(record);
        String newValue = readFieldValue(field);
        record.updateProperty(field, newValue);
        System.out.println("The record updated!\n");
    }

    private String readFieldName(Record record) {
        String field;
        Collection<String> elements;
        do {
            elements = record.propertiesAsStrings();
            String propertiesString = String.join(", ", elements);
            System.out.printf("Select a field (%s): ", propertiesString);
            field = scanner.nextLine();
        } while (!elements.contains(field));
        return field;
    }

    private String readFieldValue(String field) {
        System.out.print("Enter " + field + ": ");
        return scanner.nextLine();
    }

    private void actionCount() {
        System.out.println("The Phone Book has " + phoneBook.size() + " records.\n");
    }

    private void actionInfo() {
        printPhoneBook();
        int index = readRecordIndex("Enter index to show info: ");
        System.out.println(phoneBook.get(index).fullInfo() + "\n");
    }

    private Record readRecord() {
        while (true) {
            System.out.print("Enter the type (person, organization): ");
            String type = scanner.nextLine();
            switch (type) {
                case "person":
                    return readPersonRecord();
                case "organization":
                    return readOrganisationRecord();
                default:
                    System.out.println("Bad input!");
            }
        }
    }

    private PersonRecord readPersonRecord() {
        Name name = readRequire("name", Name::new);
        Surname surname = readRequire("surname", Surname::new);
        Optional<BirthDate> birthDateOptional = readOptional("birth date", BirthDate::new);
        Optional<Gender> genderOptional = readOptional("gender (M, F)", Gender::new);
        Optional<PhoneNumber> numberOptional = readOptional("phone number", PhoneNumber::new);

        PersonContact contact = new PersonContact(name, surname);
        birthDateOptional.ifPresent(contact::setBirthDate);
        genderOptional.ifPresent(contact::setGender);
        numberOptional.ifPresent(contact::setPhoneNumber);

        return new PersonRecord(contact);
    }

    private OrganisationRecord readOrganisationRecord() {
        OrganisationName organizationName = readRequire("organization name", OrganisationName::new);
        Optional<Address> address = readOptional("address", Address::new);
        Optional<PhoneNumber> number = readOptional("number", PhoneNumber::new);

        OrganisationContact contact = new OrganisationContact(organizationName);
        address.ifPresent(contact::setAddress);
        number.ifPresent(contact::setPhoneNumber);

        return new OrganisationRecord(contact);
    }

    private <T> T readRequire(String messageSuffix, Function<String, T> function) {
        while (true) {
            Optional<T> input = readOptional(messageSuffix, function);
            if (input.isPresent()) {
                return input.get();
            }
        }
    }

    private <T> Optional<T> readOptional(String messageSuffix, Function<String, T> function) {
        try {
            System.out.print("Enter the " + messageSuffix + ": ");
            return Optional.of(
                    function.apply(
                            scanner.nextLine()));
        } catch (IllegalArgumentException ignore) {
            System.out.println("Bad " + messageSuffix + "!");
            return Optional.empty();
        }
    }
}
