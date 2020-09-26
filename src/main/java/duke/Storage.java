package duke;

import duke.tasks.Task;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import static duke.Duke.taskList;

/**
 * File storage class to handle saving to and retrieving from local hard drive
 * Locally saved tasks are of the form: taskType|0 or 1|taskDescription e.g E|1|taskDescription
 * 0 represents that the task is not done
 * 1 represents that the task is done
 */

public class Storage {

    public static final String TICK = "\u2713";
    public static final String CROSS = "\u2718";
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void showSavedContents() {
        try {
            initStorage();
        } catch (IOException e) {
            System.out.println("File Error!");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves info from the local file and adds it to the tasklist
     * Creates folder if doesn't exist
     * Creates text file if doesn't exist
     * @throws IOException if file not found or file is corrupted
     */
    public void initStorage() throws IOException {
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
                taskList.addTodoToList(taskDescription,false);
                counter++;
                break;
            case "D":
                taskList.addDeadlineToList(taskDescription,false);
                counter++;
                break;
            case "E":
                taskList.addEventToList(taskDescription,false);
                counter++;
                break;
            default:
                System.out.println("Invalid Format!");
                break;
            }

            //Mark as done
            if (Integer.parseInt(lines[1]) == 1) {
                taskList.getList().get(counter - 1).setIsDone(true);
            }
        }
        taskList.listTasks();
    }

    public void localSave(String taskType, String taskDescription) {
        try {
            writeToFile(taskType, taskDescription);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes to saved local file when a new task is added
     * Saves it in the form of taskType|0 or 1|taskDescription e.g E|1|taskDescription
     * @param taskType type of task
     * @param taskDescription formatted task description from user input
     * @throws IOException if file not found or corrupted
     */
    private void writeToFile(String taskType, String taskDescription) throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt", true);
        String textToAdd = taskType + "|0|" + taskDescription + "\n";
        fw.write(textToAdd);
        fw.close();
    }

    public void localSaveAll() {
        try {
            writeListToFile();
        } catch (IOException e) {
            System.out.println("Error! Cannot save all to file");
            e.printStackTrace();
        }
    }

    /**
     * Writes to local file when a task is modified i.e marked as done or deleted
     * @throws IOException if file not found or corrupted
     */
    public void writeListToFile() throws IOException {
        FileWriter fw = new FileWriter("./data/duke.txt");
        String textToAdd = "";
        for (Task task : taskList.getList()) {
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
}
