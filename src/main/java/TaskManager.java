import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
                case "add" -> tasks = add(CSVFilePath, tasks);
                case "remove" -> tasks = remove(CSVFilePath, tasks);
                case "list" -> list(tasks);
            }

            System.out.println(ConsoleColors.BLUE + "Please select an option:");
            System.out.println(ConsoleColors.RESET + "add\nremove\nlist\nexit");
            command = terminalReader.nextLine().trim();
        }
        System.out.println(ConsoleColors.YELLOW + "Bye, bye.");
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
            System.out.println(ConsoleColors.RED + "incorrect file path");
            return new String[0][0];
        }
    }

    public static String[][] add(Path tasksFilePath, String[][] tasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please add task description:");
        String taskDes = scanner.nextLine().trim();
        System.out.println("Please add task due date [YYYY-MM-DD]:");
        String takDueDate = scanner.nextLine().trim();

        System.out.println("Is your task important [true/false]:");
        if (scanner.hasNextBoolean()) {
            String taskIsImportant = scanner.next();

            String[] task = {taskDes, takDueDate, taskIsImportant};
            try {
                Files.writeString(tasksFilePath, String.join(", ", task) + "\n", StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println(ConsoleColors.RED + "incorrect file path");
            }
            return ArrayUtils.add(tasks, task);
        } else {
            System.out.println(ConsoleColors.RED + "provided value is neither 'true' nor 'false'");
            return tasks;
        }
    }

    public static String[][] remove(Path tasksFilePath, String[][] tasks) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select task to remove by providing it's number:");
        if (scanner.hasNextInt()) {
            int taskNo = scanner.nextInt();
            if (taskNo > 0 && taskNo < tasks.length) {
                String[][] updatedTasks = ArrayUtils.remove(tasks, taskNo);

                try {
                    StringBuilder updatedTasksStr = new StringBuilder();
                    for (String[] task : updatedTasks) {
                        updatedTasksStr.append(String.join(", ", task));
                        updatedTasksStr.append("\n");
                    }
                    Files.writeString(tasksFilePath, updatedTasksStr.toString());
                } catch (IOException e) {
                    System.out.println(ConsoleColors.RED + "incorrect file path");
                }
                return updatedTasks;
            } else {
                System.out.println(ConsoleColors.RED + "task with provided index does not exist");
            }
        } else {
            System.out.println(ConsoleColors.RED + "provided value is not a number");
        }
        return tasks;
    }

    public static void list(String[][] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + " : ");
            System.out.println(String.join("  ", tasks[i]));
        }
    }
}
