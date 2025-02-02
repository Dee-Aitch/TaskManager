import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        tasks = new ArrayList<>(CSVHandler.loadTasksFromFile());

        boolean running = true;

        while (running) {
            try {
                System.out.println("\nTask Manager");
                System.out.println("1. Add task");
                System.out.println("2. Display all tasks");
                System.out.println("3. Display completed tasks");
                System.out.println("4. Display incomplete tasks");
                System.out.println("5. Mark task as completed");
                System.out.println("6. Delete task");
                System.out.println("7. Exit");

                System.out.print("Choose an option: ");
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addTask();
                    case 2 -> displayTasks();
                    case 3 -> displayCompletedTasks();
                    case 4 -> displayUncompletedTasks();
                    case 5 -> markTaskAsCompleted();
                    case 6 -> deleteTask();
                    case 7 -> {
                        CSVHandler.saveTasksToFile(tasks);
                        running = false;
                        System.out.println("Thank you for using the Task Manager!");
                    }
                    default -> System.out.println("Invalid option. Please choose a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
            }
        }
    }



    private static void addTask() {
        System.out.print("\nPlease provide the task title: ");
        String title = scanner.nextLine();

        System.out.print("Please provide the task description: ");
        String description = scanner.nextLine();

        tasks.add(new Task(title, description));
        System.out.println("\nTask added!");
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks to display.");
            return;
        }

        System.out.println("\nTask list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getTitle() + " (\nDescription: " + task.getDescription() + ", Completed: " + task.isCompleted() + ")");
        }
    }

    private static void displayCompletedTasks() {
        boolean hasCompletedTasks = false;

        System.out.println("\nCompleted tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isCompleted()) {
                System.out.println((i + 1) + ". " + task.getTitle() + " (\nDescription: " + task.getDescription() + ")");
                hasCompletedTasks = true;
            }
        }

        if (!hasCompletedTasks) {
            System.out.println("No completed tasks.");
        }
    }

    private static void displayUncompletedTasks() {
        boolean hasUncompletedTasks = false;

        System.out.println("\nIncomplete tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (!task.isCompleted()) {
                System.out.println((i + 1) + ". " + task.getTitle() + " (Description: " + task.getDescription() + ")");
                hasUncompletedTasks = true;
            }
        }

        if (!hasUncompletedTasks) {
            System.out.println("\n" + "No incomplete tasks.");
        }
    }


    private static void markTaskAsCompleted() {
        displayTasks();

        if (tasks.isEmpty()) return;

        try {
            System.out.print("Please provide the task number to mark as completed: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).setCompleted(true);
                System.out.println("\n" + "Task marked as completed.");
            } else {
                System.out.println("\n" + "Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter the task number.");
        }
    }


    private static void deleteTask() {
        displayTasks();

        if (tasks.isEmpty()) return;

        try {
            System.out.print("Please provide the task number to delete: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (index >= 0 && index < tasks.size()) {
                tasks.remove(index);
                System.out.println("\n" + "Task deleted.");
            } else {
                System.out.println("\n" + "Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter the task number.");
        }
    }

}
