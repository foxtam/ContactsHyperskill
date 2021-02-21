package contacts;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private final PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("Enter the name of the person:");
        final String name = scanner.nextLine();

        System.out.println("Enter the surname of the person:");
        final String surname = scanner.nextLine();

        System.out.println("Enter the number:");
        final String number = scanner.nextLine();

        phoneBook.add(new Contact(name, surname, number));

        System.out.println(phoneBook);
    }
}
