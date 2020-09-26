package duke.tasks;

/**
 * Event class: A subclass of Task that handles tasks which are events
 */

public class Event extends Task {
    protected final String typeOfTask = "E";

    public Event(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * @return the task preceded by type of task and whether the task is done
     * e.g [E][X] Attend birthday party (at: Beach Resort)
     */
    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + getStatusIcon() + " " + getDescription();
    }
}
