public class Deadline extends Task {
    protected final String typeOfTask = "[D]";

    public Deadline(String description) {
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