package duke;

<<<<<<< Updated upstream
=======
import java.util.Arrays;
import static duke.Duke.taskList;

/**
 * Exception class for handling Duke commands
 */

>>>>>>> Stashed changes
public class DukeException extends Exception {
    private String typeOfTask;

    public DukeException(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    /**
     * @return the appropriate error message with the required rectification
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a " + typeOfTask + " cannot be empty!";
    }
}
