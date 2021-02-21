package contacts.contact;

import contacts.PhoneNumber;
import contacts.exceptions.WrongPhoneNumberFormatException;

public class Contact {

    private String name;
    private String surname;
    private PhoneNumber phoneNumber = null;

    public Contact(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber.numberAsString();
    }

    public void setPhoneNumber(String phoneNumber) throws WrongPhoneNumberFormatException {
        try {
            this.phoneNumber = new PhoneNumber(phoneNumber);
        } catch (WrongPhoneNumberFormatException e) {
            this.phoneNumber = null;
            throw e;
        }
    }

    public boolean hasNumber() {
        return phoneNumber != null;
    }
}
