package contacts.contact;

import contacts.contact.properties.Address;
import contacts.contact.properties.OrganisationName;

import java.io.Serializable;
import java.util.Map;

public class OrganisationRecord extends AbstractRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    public OrganisationRecord(OrganisationContact contact) {
        super(contact,
                Map.of(
                        "name", s -> contact.setOrganizationName(new OrganisationName(s)),
                        "address", s -> contact.setAddress(new Address(s))));
    }
}
