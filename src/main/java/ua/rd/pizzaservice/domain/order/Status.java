package ua.rd.pizzaservice.domain.order;

public enum Status {
    DONE,
    CANCELLED,
    IN_PROGRESS(CANCELLED, DONE),
    NEW(CANCELLED, IN_PROGRESS);

    private final Status[] availableChanges;

    Status(Status... availableChanges) {
        this.availableChanges = availableChanges;
    }

    public boolean isAvailableChangeTo(Status status) {
        for (Status statusToChange : availableChanges) {
            if (statusToChange.equals(status)) {
                return true;
            }
        }
        return false;
    }
}
