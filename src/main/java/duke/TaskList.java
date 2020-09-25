package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static duke.Duke.storage;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public void addEventToList(String taskDescription, Boolean notify) {
        Event addedEvent = new Event(taskDescription);
        taskList.add(addedEvent);
        if (notify) {
            notifyUser(addedEvent);
        }
    }

    public void addDeadlineToList(String taskDescription, Boolean notify) {
        Deadline addedDeadline = new Deadline(taskDescription);
        taskList.add(addedDeadline);
        if (notify) {
            notifyUser(addedDeadline);
        }
    }

    public void addTodoToList(String taskDescription, Boolean notify) {
        Todo addedTodo = new Todo(taskDescription);
        taskList.add(addedTodo);
        if (notify) {
            notifyUser(addedTodo);
        }
    }

    public void notifyUser(Task task) {
        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public void deleteTask(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("delete OOB");
        }
        if (index <= 0) {
            throw new DukeException("delete negative");
        }


        Task deletedTask = taskList.remove(index);

        //Notify user
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + deletedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    public void markTaskAsDone(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("done OOB");
        }
        if (index <= 0) {
            throw new DukeException("done negative");
        }

        taskList.get(index).setIsDone(true);

        //notify user
        System.out.println("Nice! I've marked this task as done:\n    " + taskList.get(index));
    }

    public ArrayList<Task> findTasks(String searchWord) {
        ArrayList<Task> foundTasksList = new ArrayList<>();
        for(Task task : taskList) {
            if (task.getDescription().contains(searchWord)) {
                foundTasksList.add(task);
            }
        }
        return foundTasksList;
    }

    public void listTasks(ArrayList<Task> arrayList) {
        System.out.println("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task task : arrayList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : taskList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    public ArrayList<Task> getList() {
        return taskList;
    }
}
