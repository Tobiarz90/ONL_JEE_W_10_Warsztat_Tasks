import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner terminalReader = new Scanner(System.in);
        String command = "";
        Path CSVFilePath = Paths.get("src", "main", "java", "tasks.csv");
        String[][] tasks = updateTasksFromFile(CSVFilePath);

        while (!command.equals("exit")) {
            switch (command) {
                case "add":
                    // add
                    break;
                case "remove":
                    // remove
                    break;
                case "list":
                    // list
                    break;
            }

            System.out.println(ConsoleColors.BLUE + "Please select an option:");
            System.out.println(ConsoleColors.RESET + "add\nremove\nlist\nexit");
            command = terminalReader.nextLine().trim();
        }
        System.out.println("Bye, bye.");
    }

    public static String[][] updateTasksFromFile(Path tasksFilePath) {
        try {
            List<String> lines = Files.readAllLines(tasksFilePath);
            String[][] updatedTasks = new String[lines.size()][0];
            int i = 0;
            for (String line : lines) {
                updatedTasks[i] = line.split(", ");
                i++;
            }
            return updatedTasks;
        } catch (IOException e) {
            System.out.println("incorrect file path");
            return new String[0][0];
        }
    }
}
