package contacts.contact.properties;

public class Address {
    private final String address;

    public Address(final String address) {
        if (!address.matches("[\\w ]+")) throw new IllegalArgumentException(address);
        this.address = address;
    }

    @Override
    public String toString() {
        return address;
    }
}
