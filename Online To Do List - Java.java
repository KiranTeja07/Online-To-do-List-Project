
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Task {
    private String description;
    
    public Task(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}

class User {
    private String username;
    private String password;
    private List<Task> tasks;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.tasks = new ArrayList<>();
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public List<Task> getTasks() {
        return tasks;
    }
}

public class ToDoList {
    public static void main(String[] args) {
        Map<String, User> users = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to your online to-do list!");
        
        while (true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String newUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String newPassword = scanner.nextLine();
                    users.put(newUser, new User(newUser, newPassword));
                    System.out.println("User registered.");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    
                    User loggedInUser = users.get(username);
                    if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                        handleUserTasks(loggedInUser, scanner);
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the to-do list app!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
    
    private static void handleUserTasks(User user, Scanner scanner) {
        while (true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Add task");
            System.out.println("2. Edit task");
            System.out.println("3. Delete task");
            System.out.println("4. Display tasks");
            System.out.println("5. Logout");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String taskDescription = scanner.nextLine();
                    Task newTask = new Task(taskDescription);
                    user.getTasks().add(newTask);
                    System.out.println("Task added: " + taskDescription);
                    break;
                case 2:
                    System.out.print("Enter task number to edit: ");
                    int editIndex = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    if (editIndex >= 0 && editIndex < user.getTasks().size()) {
                        System.out.print("Enter new task description: ");
                        String newDescription = scanner.nextLine();
                        user.getTasks().get(editIndex).setDescription(newDescription);
                        System.out.println("Task edited.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 3:
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = scanner.nextInt() - 1;
                    if (deleteIndex >= 0 && deleteIndex < user.getTasks().size()) {
                        user.getTasks().remove(deleteIndex);
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Invalid task number.");
                    }
                    break;
                case 4:
                    System.out.println("\nYour to-do list:");
                    for (int i = 0; i < user.getTasks().size(); i++) {
                        Task task = user.getTasks().get(i);
                        System.out.println((i + 1) + ". " + task.getDescription());
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the to-do list app!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
