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
    public static TaskList taskList = new TaskList();
    public static Parser parser = new Parser();
    public static Storage storage = new Storage("./data/duke.txt");;
    public static String command;
    public static int index;

    /**
     * Displays intro message
     * Retrieves and displays the saved local file in a list form
     * Runs the main program that takes in user inputs
     */
    public static void main(String[] args) {
        introMessage();
        storage.showSavedContents();
        System.out.println("____________________________________________________________");

        run();
    }

    /**
     * Takes in the user inputs and parses it into Duke understandable commands
     */
    public static void run() {
        //Scanner in
        Scanner in = new Scanner(System.in);
        String userInput;

        //Check user input and add/modify accordingly
        while (true) {
            try {
                userInput = in.nextLine();
                System.out.println("____________________________________________________________");
                parser.setUserInput(userInput);
                command = parser.parseCommand();
                switch (command) {
                case "todo":
                    String todo = parser.parseStringTodo();
                    taskList.addTodoToList(todo, true);
                    storage.localSave("T", todo);
                    break;
                case "deadline":
                    String deadline = parser.parseStringDeadline();
                    taskList.addDeadlineToList(deadline, true);
                    storage.localSave("D", deadline);
                    break;
                case "event":
                    String event = parser.parseStringEvent();
                    taskList.addEventToList(event, true);
                    storage.localSave("E", event);
                    break;
                case "list":
                    taskList.listTasks();
                    break;
                case "done":
                    index = parser.parseStringDone();
                    taskList.markTaskAsDone(index);
                    storage.localSaveAll();
                    break;
                case "delete":
                    index = parser.parseStringDelete();
                    taskList.deleteTask(index);
                    storage.localSaveAll();
                    break;
                case "find":
                    String searchWord = parser.parseStringFind();
                    ArrayList<Task> foundTasksList = taskList.findTasks(searchWord);
                    taskList.listTasks(foundTasksList);
                    break;
                case "bye":
                    exitDuke();
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! Please input a valid task number greater than 0");
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void introMessage() {
        //Intro message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        System.exit(0);
    }
}
