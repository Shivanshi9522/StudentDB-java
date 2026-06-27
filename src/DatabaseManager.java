import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:students.db";

    public DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");  // This fixes the driver error
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite driver not found!");
        }
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     "name TEXT NOT NULL, age INTEGER, " +
                     "course TEXT, email TEXT UNIQUE)";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) { 
            System.out.println(e.getMessage()); 
        }
    }

    public void addStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, age, course, email) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getCourse());
            pstmt.setString(4, student.getEmail());
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("age"), rs.getString("course"), rs.getString("email")));
            }
        }
        return students;
    }

    public void updateStudent(int id, String name, int age, String course, String email) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, course=?, email=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name); 
            pstmt.setInt(2, age); 
            pstmt.setString(3, course);
            pstmt.setString(4, email); 
            pstmt.setInt(5, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Student updated!" : "Student ID not found.");
        }
    }

    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Student deleted!" : "Student ID not found.");
        }
    }
}