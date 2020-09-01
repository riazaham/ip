public class Event extends Task {
    protected final String typeOfTask = "[E]";

    public Event(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public String toString() {
        return getTypeOfTask() + getStatusIcon() + " " + getDescription();
    }
}
