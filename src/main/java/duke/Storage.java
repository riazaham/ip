package duke;

import duke.tasks.Task;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

import static duke.Duke.taskList;


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
        } catch (IOException | DukeException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    public void initStorage() throws IOException, DukeException {
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
            writeToFile("T", taskDescription);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //To write to file when a new task is added
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

    //To write to file when a a task is modified
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
