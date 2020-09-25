package duke;

public class DukeException extends Exception {
    private String typeOfTask;

    public DukeException(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a " + typeOfTask + " cannot be empty!";
    }
}
