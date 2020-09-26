package duke.tasks;

/**
 * Task class for creating basic task objects that hold represent the tasks input by the user
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }

    /**
     * Currently only used for subclasses to override
     * Cannot be called by user as a command
     * @return the task preceded by whether the task is done
     * e.g [X] Submit assignment
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
