public class Todo extends Task {
    protected final String typeOfTask = "[T]";

    public Todo(String description) {
        super(description);
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public String toString() {
        return getTypeOfTask() + getStatusIcon() + " " + getDescription();
    }
}
