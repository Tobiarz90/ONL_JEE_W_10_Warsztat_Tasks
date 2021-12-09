import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        Scanner terminalReader = new Scanner(System.in);
        String command = "";
        String[][] tasks = new String[0][0];

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
}
