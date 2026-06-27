public class Student {
    private int id;
    private String name;
    private int age;
    private String course;
    private String email;

    public Student(String name, int age, String course, String email) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
    }
    
    public Student(int id, String name, int age, String course, String email) {
        this.id = id; this.name = name; this.age = age; 
        this.course = course; this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Age: %d | Course: %s | Email: %s", 
                id, name, age, course, email);
    }
}