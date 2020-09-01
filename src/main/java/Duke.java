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
                switch (userInputs[0]) {
                case "todo":
                    String todo = "";
                    for (int i = 1; i < userInputs.length; i++) {
                        todo += userInputs[i] + " ";
                    }
                    Todo addedTodo = new Todo(todo);
                    addTodoToList(addedTodo);
                    break;
                case "deadline":
                    //Split deadline -> format -> "description (by: deadline)"
                    String deadline = getFormattedString(userInputs, "/by");
                    Deadline addedDeadline = new Deadline(deadline);
                    addDeadlineToList(addedDeadline);
                    break;
                case "event":
                    String event = getFormattedString(userInputs, "/at");
                    Event addedEvent = new Event(event);
                    addEventToList(addedEvent);
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listCount; i++) {
                        System.out.println((i + 1) + ". " + userInputList[i]);
                    }
                    break;
                case "done":
                    //List indexed from 0, offset by 1
                    int listNumber = Integer.parseInt(userInputs[1]) - 1;
                    userInputList[listNumber].setIsDone(true);
                    System.out.println("Nice! I've marked this task as done:\n    " + userInputList[listNumber]);
                    break;
                default:
                    Task addedTask = new Task(userInput);
                    addTaskToList(addedTask);
                    break;
                }
                System.out.println("____________________________________________________________");
            }
        } while (!userInput.equals("bye"));

        exitDuke();
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

    public static void addEventToList(Event addedEvent) {
        //Add into list
        userInputList[listCount] = addedEvent;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedEvent);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void addDeadlineToList(Deadline addedDeadline) {
        //Add into list
        userInputList[listCount] = addedDeadline;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedDeadline);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }

    public static void addTodoToList(Todo addedTodo) {
        //Add into list
        userInputList[listCount] = addedTodo;
        listCount++;

        //Notify user
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + addedTodo);
        System.out.println("Now you have " + listCount + " tasks in the list.");
    }
}
