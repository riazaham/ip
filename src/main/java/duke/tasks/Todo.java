package duke.tasks;

/**
 * Todo class: A subclass of Task that handles tasks that are todos
 */

public class Todo extends Task {
    protected final String typeOfTask = "T";

    public Todo(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * @return the task preceded by type of task and whether the task is done
     * e.g [T][X] Go for a morning workout
     */
    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + getStatusIcon() + " " + getDescription();
    }
}
