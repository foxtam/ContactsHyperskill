package contacts.contact;

import contacts.contact.properties.PhoneNumber;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractContact implements Contact {

    protected static final String noDataStub = "[no data]";
    private final LocalDateTime creationDate = LocalDateTime.now();
    private LocalDateTime editDate = creationDate;
    private PhoneNumber phoneNumber;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getEditDate() {
        return editDate;
    }

    public PhoneNumber getPhoneNumber() {
        return Objects.requireNonNull(phoneNumber);
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateEditDate();
    }

    protected void updateEditDate() {
        this.editDate = LocalDateTime.now();
    }

    @Override
    public boolean hasNumber() {
        return phoneNumber != null;
    }

    protected String orNoDataMessage(Object obj) {
        return obj == null ? noDataStub : obj.toString();
    }
}
