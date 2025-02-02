import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHandler {
    private static final String FILE_NAME = "tasks.csv";

    public static void saveTasksToFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                String line = task.getTitle() + "," + task.getDescription() + "," + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("\n" + "Error while saving tasks: " + e.getMessage());
        }
    }

    public static List<Task> loadTasksFromFile() {
        List<Task> tasks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean isCompleted = Boolean.parseBoolean(parts[2]);
                    tasks.add(new Task(title, description));
                    tasks.get(tasks.size() - 1).setCompleted(isCompleted);
                }
            }
            System.out.println("Tasks loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, a new one will be created.");
        } catch (IOException e) {
            System.out.println("\n" + "Error while loading tasks: " + e.getMessage());
        }

        return tasks;
    }
}
