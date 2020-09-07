import java.util.Scanner;

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
 * bye                                                -> system exits after exit message
 * any other input                                    -> echos the input and adds it to a list
 */

public class Duke {
    //Variables
    public static int listCount = 0;
    public static Task[] userInputList = new Task[100];

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
                        addTodoToList(userInputs);
                        break;
                    case "deadline":
                        addDeadlineToList(userInputs);
                        break;
                    case "event":
                        addEventToList(userInputs);
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "done":
                        markTaskAsDone(userInputs);
                        break;
                    default:
                        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        break;
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! The description of done needs to be followed by a task number." );
                } catch (NullPointerException e) {
                    System.out.println("OOPS!!! The specified task does not exist. There are only " + listCount + " tasks");
                }
                System.out.println("____________________________________________________________");
            }
        } while (!userInput.equals("bye"));

        exitDuke();
    }

    public static void markTaskAsDone(String[] userInputs) throws ArrayIndexOutOfBoundsException, NullPointerException {
        //Check for only 'done'
        if (userInputs.length == 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        //List indexed from 0, offset by 1
        int listNumber = Integer.parseInt(userInputs[1]) - 1;
        userInputList[listNumber].setIsDone(true);
        System.out.println("Nice! I've marked this task as done:\n    " + userInputList[listNumber]);
    }

    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i + 1) + ". " + userInputList[i]);
        }
    }

    public static void introMessage() {
        //Intro message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
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

    public static void addTaskToList(Task addedTask) {
        //Add Task to list
        userInputList[listCount] = addedTask;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedTask);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void addEventToList(String[] userInputs) throws DukeException {
        //Check for only "event"
        if (userInputs.length == 1) {
            throw new DukeException("event");
        }

        //Split event -> format -> "description (at: event)"
        String event = getFormattedString(userInputs, "/at");
        Event addedEvent = new Event(event);

        //Add into list
        userInputList[listCount] = addedEvent;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedEvent);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void addDeadlineToList(String[] userInputs) throws DukeException {
        //Check for only "deadline"
        if (userInputs.length == 1) {
            throw new DukeException("deadline");
        }

        //Split deadline -> format -> "description (by: deadline)"
        String deadline = getFormattedString(userInputs, "/by");
        Deadline addedDeadline = new Deadline(deadline);

        //Add into list
        userInputList[listCount] = addedDeadline;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedDeadline);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void addTodoToList(String[] userInputs) throws DukeException {
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

        //Add into list
        userInputList[listCount] = addedTodo;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedTodo);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }
}
