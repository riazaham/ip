package duke.tasks;

import duke.tasks.Task;

/**
 * Deadline class: A subclass of Task that handles tasks with deadlines
 * Added in the following format: deadline stuff /by date
 * Date is taken in the following format: dd/mm/yyyy
 */

public class Deadline extends Task {
    protected final String typeOfTask = "D";

    public Deadline(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * @return the task preceded by type of task and whether the task is done
     * e.g [D][X] Submit assignment (by: Sunday 2359)
     */
    @Override
    public String toString() {
        return "[" + getTypeOfTask() + "]" + getStatusIcon() + " " + getDescription();
    }
}
