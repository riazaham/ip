package duke;

import java.util.Arrays;
import static duke.Duke.taskList;

/**
 * Exception class for handling Duke commands
 */
public class DukeException extends Exception {
    private String error;
    private final String[] taskTypes = new String[] {"todo","deadline","event", "done", "delete", "find"};
    private final String[] taskIndexOutOfBounds = new String[] {"done OOB", "delete OOB"};
    private final String[] taskIndexNegative = new String[] {"done negative", "delete negative"};

    public DukeException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    /**
     * @return the appropriate error message with the required rectification
     */
    @Override
    public String toString() {
        if (Arrays.asList(taskTypes).contains(error)) {
            return "OOPS!!! The description of a " + error + " cannot be empty!";
        }
        else if (Arrays.asList(taskIndexOutOfBounds).contains(error)) {
            return "OOPS!!! No such task exist\n" +
                    "There are only " + taskList.getList().size() + " tasks in your list";
        }
        else if (Arrays.asList(taskIndexNegative).contains(error)) {
            return "OOPS!!! Please input a valid task number greater than 0";
        }
        return "OOPS!!! Unknown error occurred";
    }
}
