package contacts;

import contacts.exceptions.WrongPhoneNumberFormatException;

import java.util.regex.Pattern;

public class PhoneNumber {
    private static final Pattern phoneNumberPattern =
            Pattern.compile("\\+?(\\w+[- ](\\(\\w{2,})\\)|\\w+|(\\(\\w+)\\))([- ]\\w{2,})*");

    private final String number;

    public PhoneNumber(String number) throws WrongPhoneNumberFormatException {
        if (!isValidPhoneNumber(number)) {
            throw new WrongPhoneNumberFormatException();
        }
        this.number = number;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumberPattern.matcher(phoneNumber).matches();
    }

    public String numberAsString() {
        return number;
    }
}
