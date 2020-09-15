package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import javax.sound.midi.SysexMessage;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Chatbot for managing tasks
 *
 * Current commands:
 * todo "task description"                            -> adds todo task to list
 * deadlilne "task description /by deadline"          -> adds deadline task to list
 * event "task description /at event"                 -> adds event task to list
 * list                                               -> shows the list of tasks
 * done "index"                                       -> marks the task at that index as done
 * e.g done 1                                         -> marks the 1st task as done
 * delete "index"                                     -> deletes the task at that index
 * e.g delete 1                                       -> deletes the 1st task from the list
 * bye                                                -> system exits after exit message
 * any other input                                    -> echos the input and adds it to a list
 */

public class Duke {
    //Variables
    public static ArrayList<Task> userInputList = new ArrayList<>();
    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";

    public static void main(String[] args) {
        /*
         * String logo = " ____        _        \n"
         *         + "|  _ \\ _   _| | _____ \n"
         *         + "| | | | | | | |/ / _ \\\n"
         *         + "| |_| | |_| |   <  __/\n"
         *         + "|____/ \\__,_|_|\\_\\___|\n";
         * System.out.println("Hello from\n" + logo);
         */

        introMessage();
        try {
            getTasksFromFile();
        } catch (FileNotFoundException f) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("____________________________________________________________");

        //Scanner in
        Scanner in = new Scanner(System.in);
        String userInput;

        //Check user input and add/modify accordingly
        do {
            userInput = in.nextLine();
            String[] userInputs = userInput.split("\\s+");
            if (!userInput.equals("bye")) { //this line is so that bye is not 'added'
                System.out.println("____________________________________________________________");
                try {
                    switch (userInputs[0]) {
                    case "todo":
                        addTodoToList(userInputs, true);
                        break;
                    case "deadline":
                        addDeadlineToList(userInputs, true);
                        break;
                    case "event":
                        addEventToList(userInputs, true);
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "done":
                        markTaskAsDone(userInputs);
                        break;
                    case "delete":
                        deleteTask(userInputs);
                        break;
                    default:
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        break;
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (ArrayIndexOutOfBoundsException e) { //done and delete
                    System.out.println("OOPS!!! The command needs to be followed by a task number." );
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The specified task does not exist. There are only " +
                            userInputList.size() + " tasks");
                } catch (IOException e) {
                    System.out.println("OOPS!!! Something went wrong." );
                    e.printStackTrace();
                }
                System.out.println("____________________________________________________________");
            }
        } while (!userInput.equals("bye"));

        exitDuke();
    }

    public static void getTasksFromFile() throws IOException {
        File f = new File("./data/duke.txt");
        boolean mkdir = new File("./data").mkdir();
        boolean result = f.createNewFile();
        if (mkdir | result) {
            System.out.println("New file created at [project root]/data/duke.txt");
            System.out.println("____________________________________________________________");
        }
        Scanner s = new Scanner(f);
        int counter = 0;
        while(s.hasNext()) {
            String line = s.nextLine();
            String[] lines = line.split("\\|");
            String taskDescription = lines[2];

            switch (lines[0]) {
            case "T":
                Todo addedTodo = new Todo(taskDescription);
                userInputList.add(addedTodo);
                counter++;
                break;
            case "D":
                Deadline addedDeadline = new Deadline(taskDescription);
                userInputList.add(addedDeadline);
                counter++;
                break;
            case "E":
                Event addedEvent = new Event(taskDescription);
                userInputList.add(addedEvent);
                counter++;
                break;
            default:
                System.out.println("Invalid Format!");
                break;
            }

            //Mark as done
            if (Integer.parseInt(lines[1]) == 1) {
                userInputList.get(counter - 1).setIsDone(true);
            }
            //System.out.println(s.nextLine());
        }
        listTasks();
    }

    public static void deleteTask(String[] userInputs) throws ArrayIndexOutOfBoundsException, IOException {
        //Check for only 'delete'
        if (userInputs.length == 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int indexToBeDeleted = Integer.parseInt(userInputs[1]) - 1;
        Task deletedTask = userInputList.remove(indexToBeDeleted);

        //Modify local file
        writeListToFile();

        //Notify user
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + deletedTask);
        System.out.println("Now you have " + userInputList.size() + " tasks in the list");
    }

    public static void markTaskAsDone(String[] userInputs) throws ArrayIndexOutOfBoundsException, IOException {
        //Check for only 'done'
        if (userInputs.length == 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //List indexed from 0, offset by 1
        int listNumber = Integer.parseInt(userInputs[1]) - 1;
        userInputList.get(listNumber).setIsDone(true);

        //Modify local file
        writeListToFile();

        //notify user
        System.out.println("Nice! I've marked this task as done:\n    " + userInputList.get(listNumber));
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : userInputList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }

    public static void introMessage() {
        //Intro message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    //Split at delimiter with format -> before (delimiter: after)
    public static String getFormattedString(String[] userInputs, String delimiter) {
        String text = "";
        for (int i = 1; i < userInputs.length; i++) {
            if (userInputs[i].equals(delimiter)) { //split at delimiter
                switch (delimiter){
                case "/by":
                    text += "(by: ";
                    break;
                case "/at":
                    text += "(at: ";
                    break;
                default:
                    text += delimiter;
                }
            } else if (i == userInputs.length - 1) { //last word
                text += userInputs[i] + ")";
            } else {
                text += userInputs[i] + " ";
            }
        }
        return text;
    }

    public static void exitDuke() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }

    //Not implemented
    public static void addTaskToList(Task addedTask, Boolean notify) {
        //Add duke.tasks.Task to list
        userInputList.add(addedTask);

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedTask);
        System.out.println("Now you have " + userInputList.size() + " tasks in the list.");
    }

    public static void addEventToList(String[] userInputs, Boolean notify) throws DukeException {
        //Check for only "event"
        if (userInputs.length == 1) {
            throw new DukeException("event");
        }

        //Split event -> format -> "description (at: event)"
        String event = getFormattedString(userInputs, "/at");
        Event addedEvent = new Event(event);

        //Add into local file
        try {
            writeToFile("E", event);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add into list
        userInputList.add(addedEvent);

        if (notify) {
            notifyUser(addedEvent);
        }
    }

    public static void addDeadlineToList(String[] userInputs, Boolean notify) throws DukeException {
        //Check for only "deadline"
        if (userInputs.length == 1) {
            throw new DukeException("deadline");
        }

        //Split deadline -> format -> "description (by: deadline)"
        String deadline = getFormattedString(userInputs, "/by");
        Deadline addedDeadline = new Deadline(deadline);

        //Add into local file
        try {
            writeToFile("D", deadline);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add into list
        userInputList.add(addedDeadline);

        if (notify) {
            notifyUser(addedDeadline);
        }
    }

    public static void addTodoToList(String[] userInputs, Boolean notify)
            throws DukeException {
        String todo = "";

        //Check for only "todo"
        if (userInputs.length == 1) {
            throw new DukeException("todo");
        }

        //Add words after todo into a string
        for (int i = 1; i < userInputs.length; i++) {
            todo += userInputs[i] + " ";
        }
        Todo addedTodo = new Todo(todo);

        //Add into local file
        try {
            writeToFile("T", todo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add into list
        userInputList.add(addedTodo);

        if (notify) {
            notifyUser(addedTodo);
        }
    }

    public static void writeToFile(String taskType, String taskDescription) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt", true);
        String textToAdd = taskType + "|0|" + taskDescription + "\n";
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String textToAdd = "";
        for (Task task : userInputList) {
            String[] taskDescriptions = task.toString().split("\\s+");
            String taskType = String.valueOf(taskDescriptions[0].charAt(1));
            String done = String.valueOf(taskDescriptions[0].charAt(4)).equals(TICK) ? "1" : "0";
            String taskDescription = String.join(" ",
                    Arrays.copyOfRange(taskDescriptions, 1, taskDescriptions.length));
            textToAdd = taskType + "|" + done + "|" + taskDescription + "\n";
            fw.write(textToAdd);
        }
        fw.close();
    }


    public static void notifyUser(Task task) {
        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println("Now you have " + userInputList.size() + " tasks in the list.");
    }
}
