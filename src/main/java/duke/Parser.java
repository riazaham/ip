package duke;

<<<<<<< Updated upstream
=======
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parser class to make sense of the inputs from the user
 * Currently parses all commands
 * Throws DukeExceptions for invalid commands
 */

>>>>>>> Stashed changes
public class Parser {

    public String userInput;
    public String[] userInputs;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        splitUserInput();
    }

    public void splitUserInput() {
        userInputs = userInput.split("\\s+");
    }

    /**
     * Check if the command is a valid Duke command
     * @return the command if it is valid
     * @throws DukeException if command is not valid
     */
    public String parseCommand() throws DukeException {
        String command = userInputs[0];
        checkException(command);
        return command;
    }

    /**
     * Checks if user input valid command
     * @param command takes in the command input by user
     * @throws DukeException when user misses out description
     * @throws ArrayIndexOutOfBoundsException when user misses out task no. or inputs invalid task no.
     */
    public void checkException(String command) throws DukeException, ArrayIndexOutOfBoundsException {
        if (userInputs.length == 1) {
            switch (command) {
            case "todo":
                throw new DukeException("todo");
            case "deadline":
                throw new DukeException("deadline");
            case "event":
                throw new DukeException("event");
            case "done":
                throw new ArrayIndexOutOfBoundsException();
            case "delete":
                throw new ArrayIndexOutOfBoundsException();
            case "find":
                throw new ArrayIndexOutOfBoundsException();
            }
        }
    }

    /**
     * Split at delimiter "/at" for event command
     * @return format for event tasks: e.g Attend Birthday Party (at: Beach Resort)
     */
    public String parseStringEvent() {
        String text = "";
        for (int i = 1; i < userInputs.length; i++) {
            if (userInputs[i].equals("/at")) { //split at delimiter
                text += "(at: ";
            } else if (i == userInputs.length - 1) { //Last letter
                text += userInputs[i] + ")";
            } else {
                text += userInputs[i] + " ";
            }
        }
        return text;
    }

    /**
     * Split at delimiter "/by" for event command
     * @return format for event tasks: e.g Submit assignment (by: Sunday 2359)
     */
    public String parseStringDeadline() {
        String text = "";
        for (int i = 1; i < userInputs.length; i++) {
            if (userInputs[i].equals("/by")) { //split at delimiter
                text += "(by: ";
            } else if (i == userInputs.length - 1) { //Last letter
                text += userInputs[i] + ")";
            } else {
                text += userInputs[i] + " ";
            }
        }
        return text;
    }

    /**
     * No delimiter to split at
     * @return format for todo tasks: e.g Submit assignment
     */
    public String parseStringTodo() {
        String text = "";
        for (int i = 1; i < userInputs.length; i++) {
            if (i == userInputs.length - 1) { //Last letter
                text += userInputs[i];
            } else {
                text += userInputs[i] + " ";
            }
        }
        return text;
    }

<<<<<<< Updated upstream
    public int parseStringDone() {
        //List indexed from 0, offset by 1
        return Integer.parseInt(userInputs[1]) - 1;
    }

    public int parseStringDelete() {
        //List indexed from 0, offset by 1
=======
    /**
     * @return integer index at which the task needs to marked done
     * @throws NumberFormatException if the task number is of invalid format (e.g string)
     */
    public int parseStringDone() throws NumberFormatException {
        return Integer.parseInt(userInputs[1]) - 1;
    }

    /**
     * @return integer index at which the task needs to deleted
     * @throws NumberFormatException if the task number is of invalid format (e.g string)
     */
    public int parseStringDelete() throws NumberFormatException {
>>>>>>> Stashed changes
        return Integer.parseInt(userInputs[1]) - 1;
    }

    /**
     * @return the searchword that the user input
     */
    public String parseStringFind() {
        return userInputs[1];
    }
<<<<<<< Updated upstream
=======

    /**
     * parses any date info
     * @param date takes in date of the format 1/1/2020
     * @return localDate object formatted as MMM dd yyyy e.g Jan 1 2020
     */
    public String parseDate(String date) {
        String[] dateElements = date.split("/");

        //Check for day format -> must be of length 2 digits (e.g 03)
        if (dateElements[0].length() == 1) {
            dateElements[0] = "0" + dateElements[0];
        }

        //Check for month format -> must be of length 2 digits (e.g 03)
        if (dateElements[1].length() == 1) {
            dateElements[1] = "0" + dateElements[1];
        }

        //Reorder date elements to yyyy-mm-dd -> format required for localDate class
        dateElements = new String[] {dateElements[2], dateElements[1], dateElements[0]};

        //Check for month format -> must be of length 2 digits (e.g 03)
        if (dateElements[1].length() == 1) {
            dateElements[1] = "0" + dateElements[1];
        }

        //Check for date format -> must be of length 2 digits (e.g 03)
        if (dateElements[2].length() == 1) {
            dateElements[2] = "0" + dateElements[2];
        }

        String dateFormatted = String.join("-", dateElements);
        System.out.println(dateFormatted);
        LocalDate localDate = LocalDate.parse(dateFormatted);
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
>>>>>>> Stashed changes
}
