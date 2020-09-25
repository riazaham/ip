package duke;

public class Parser {

    public String userInput;
    public String[] userInputs;

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        splitUserInput();
    }

    public void splitUserInput() {
        userInputs = userInputs = userInput.split("\\s+");
    }

    public String parseCommand() throws DukeException {
        String command = userInputs[0];
        checkException(command);
        return command;
    }

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
            }
        }
    }

    //Split at delimiter with format -> before (delimiter: after)
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

    public int parseStringDone() {
        //List indexed from 0, offset by 1
        int index = Integer.parseInt(userInputs[1]) - 1;
        return index;
    }

    public int parseStringDelete() {
        //List indexed from 0, offset by 1
        int index = Integer.parseInt(userInputs[1]) - 1;
        return index;
    }
}
