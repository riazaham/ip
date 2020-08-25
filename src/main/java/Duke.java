import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*
         * String logo = " ____        _        \n"
         *         + "|  _ \\ _   _| | _____ \n"
         *         + "| | | | | | | |/ / _ \\\n"
         *         + "| |_| | |_| |   <  __/\n"
         *         + "|____/ \\__,_|_|\\_\\___|\n";
         * System.out.println("Hello from\n" + logo);
         */
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        //Variables
        Task[] userInputList = new Task[100];
        int listCount = 0;

        //Scanner in
        Scanner in = new Scanner(System.in);
        String userInput;

        //Echo user inputs (if not 'bye')
        //Add user input to list
        do {
            userInput = in.nextLine();
            if (!userInput.equals("bye")) { //this line is so that bye is not 'added'
                System.out.println("____________________________________________________________");
                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < listCount; i++) {
                        System.out.println((i + 1) + ". " + userInputList[i].getStatusIcon() + " " + userInputList[i].getDescription());
                    }
                } else {
                    //Split to check for 'done'
                    String[] userInputs = userInput.split("\\s+");
                    if (userInputs[0].equals("done")) {
                        //Assuming the format is 'done number'
                        int listNumber = Integer.parseInt(userInputs[1]) - 1; //List indexed from 0, offset by 1
                        userInputList[listNumber].setIsDone(true);
                        System.out.println("Nice! I've marked this task as done:\n" + userInputList[listNumber].getStatusIcon() + " " + userInputList[listNumber].getDescription());
                    } else {
                        Task newTask = new Task(userInput);
                        userInputList[listCount] = newTask;
                        listCount++;
                        System.out.println("added: " + userInput);
                    }
                }
                System.out.println("____________________________________________________________");
            }
        } while (!userInput.equals("bye"));

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
