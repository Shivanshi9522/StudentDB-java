import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static DatabaseManager db = new DatabaseManager();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Database System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt(); sc.nextLine();
            
            try {
                switch (choice) {
                    case 1: addStudent(); break;
                    case 2: viewStudents(); break;
                    case 3: updateStudent(); break;
                    case 4: deleteStudent(); break;
                    case 5: System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid choice!");
                }
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void addStudent() throws SQLException {
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Age: "); int age = sc.nextInt(); sc.nextLine();
        System.out.print("Course: "); String course = sc.nextLine();
        System.out.print("Email: "); String email = sc.nextLine();
        db.addStudent(new Student(name, age, course, email));
    }

    private static void viewStudents() throws SQLException {
        List<Student> students = db.getAllStudents();
        if (students.isEmpty()) System.out.println("No students found.");
        else students.forEach(System.out::println);
    }

    private static void updateStudent() throws SQLException {
        System.out.print("Enter Student ID to update: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("New Name: "); String name = sc.nextLine();
        System.out.print("New Age: "); int age = sc.nextInt(); sc.nextLine();
        System.out.print("New Course: "); String course = sc.nextLine();
        System.out.print("New Email: "); String email = sc.nextLine();
        db.updateStudent(id, name, age, course, email);
    }

    private static void deleteStudent() throws SQLException {
        System.out.print("Enter Student ID to delete: "); int id = sc.nextInt();
        db.deleteStudent(id);
    }
}