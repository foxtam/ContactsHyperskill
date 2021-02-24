package contacts.contact.properties;

public class OrganisationName {
    private final String organisationName;

    public OrganisationName(final String organisationName) {
        if (!organisationName.matches(".*\\w.*")) throw new IllegalArgumentException(organisationName);
        this.organisationName = organisationName;
    }

    @Override
    public String toString() {
        return organisationName;
    }
}
