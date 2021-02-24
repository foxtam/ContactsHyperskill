package contacts.contact.properties;

import java.util.regex.Pattern;

public class PhoneNumber {
    private static final Pattern phoneNumberPattern =
            Pattern.compile("(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s-]\\d{3}[\\s-]\\d{4}");

    private final String number;

    public PhoneNumber(String number) {
        if (!isValidPhoneNumber(number)) throw new IllegalArgumentException(number);
        this.number = number;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }

    @Override
    public String toString() {
        return number;
    }
}
