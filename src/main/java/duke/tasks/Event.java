package duke.tasks;

public class Event extends Task {
    protected final String typeOfTask = "[E]";

    public Event(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return getTypeOfTask() + getStatusIcon() + " " + getDescription();
    }
}
