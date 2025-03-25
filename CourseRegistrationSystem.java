import java.util.*;

class Course {
    String courseCode, title, description, schedule;
    int capacity, enrolledStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    public boolean isFull() {
        return enrolledStudents >= capacity;
    }

    public void enrollStudent() {
        if (!isFull()) enrolledStudents++;
    }

    public void dropStudent() {
        if (enrolledStudents > 0) enrolledStudents--;
    }

    @Override
    public String toString() {
        return courseCode + " - " + title + "\n" + description + "\nSchedule: " + schedule +
               "\nSlots: " + (capacity - enrolledStudents) + "/" + capacity;
    }
}

class Student {
    String studentID, name;
    List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (!course.isFull() && !registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.enrollStudent();
            System.out.println("Successfully registered for " + course.title);
        } else {
            System.out.println("Course is full or already registered.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            course.dropStudent();
            System.out.println("Dropped course: " + course.title);
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public void showRegisteredCourses() {
        System.out.println("\nCourses registered by " + name + ":");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course c : registeredCourses) {
                System.out.println("- " + c.courseCode + " " + c.title);
            }
        }
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courseList = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample Courses
        courseList.add(new Course("CSE101", "Intro to Computer Science", "Basic programming and computing concepts.", 3, "Mon-Wed 10:00 AM"));
        courseList.add(new Course("MAT201", "Calculus", "Differential and Integral Calculus.", 2, "Tue-Thu 12:00 PM"));
        courseList.add(new Course("PHY301", "Physics", "Fundamentals of Mechanics and Thermodynamics.", 2, "Mon-Wed 2:00 PM"));

        System.out.println("===== Welcome to Course Registration System =====");
        while (true) {
            System.out.println("\n1. Register a Student\n2. View Courses\n3. Register for Course\n4. Drop Course\n5. View Registered Courses\n6. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                scanner.nextLine(); // Consume invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: registerStudent(); break;
                case 2: viewCourses(); break;
                case 3: registerForCourse(); break;
                case 4: dropCourse(); break;
                case 5: viewRegisteredCourses(); break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close(); // Close scanner
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    public static void registerStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        studentList.add(new Student(id, name));
        System.out.println("Student " + name + " registered successfully!");
    }

    public static void viewCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course c : courseList) {
            System.out.println("\n" + c);
        }
    }

    public static Student findStudent() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        for (Student s : studentList) {
            if (s.studentID.equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static void registerForCourse() {
        Student student = findStudent();
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        viewCourses();
        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();

        for (Course c : courseList) {
            if (c.courseCode.equalsIgnoreCase(courseCode)) {
                student.registerCourse(c);
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public static void dropCourse() {
        Student student = findStudent();
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        student.showRegisteredCourses();
        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();

        for (Course c : student.registeredCourses) {
            if (c.courseCode.equalsIgnoreCase(courseCode)) {
                student.dropCourse(c);
                return;
            }
        }
        System.out.println("Course not found in your registered list.");
    }

    public static void viewRegisteredCourses() {
        Student student = findStudent();
        if (student != null) {
            student.showRegisteredCourses();
        } else {
            System.out.println("Student not found!");
        }
    }
}
