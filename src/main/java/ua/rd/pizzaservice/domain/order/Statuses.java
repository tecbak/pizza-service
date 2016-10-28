package ua.rd.pizzaservice.domain.order;

public enum Statuses {
    DONE,
    CANCELLED,
    IN_PROGRESS(CANCELLED, DONE),
    NEW(CANCELLED, IN_PROGRESS);

    private final Statuses[] availableChanges;

    Statuses(Statuses... availableChanges) {
        this.availableChanges = availableChanges;
    }

    public boolean isAvailableChange(Statuses status) {
        for (Statuses statusToChange : availableChanges) {
            if (statusToChange.equals(status)) {
                return true;
            }
        }
        return false;
    }
}
