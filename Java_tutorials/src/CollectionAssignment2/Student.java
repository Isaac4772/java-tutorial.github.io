package CollectionAssignment2;

import java.util.*;

public class Student {
    private int rollNo;
    private String name;
    private String address;
    private String phoneNo;

    public Student() {
    }

    public Student(int rollNo, String name, String address, String phoneNo) {
        this.rollNo = rollNo;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("\n_____Student Info_____");
        System.out.println(getRollNo() + " - " + getName() + " - " + getAddress() + " - " + getPhoneNo());
    }

    public static void main(String[] args) {
        HashSet<Student> students = new HashSet<>(Arrays.asList(
                new Student(1001, "Isaac", "Yangon", "0912345566"),
                new Student(1002, "Harry", "Yangon", "094737473733")
        ));

        students.add(new Student(1003, "Johnathan", "Mandalay", "09433432323"));

//        Display All Students
        displayStudents(students);

        HashMap<Integer, Student> studentHashMap = new HashMap<>();
        studentHashMap.put(1001, students.stream().toList().get(0));
        studentHashMap.put(1002, students.stream().toList().get(1));
        studentHashMap.put(1003, students.stream().toList().get(2));

//        Update Student data
        studentHashMap.replace(1003, new Student(1003, "John", "Yangon", "09774511344"));

//        Search Student with roll no
        studentHashMap.get(1003).display();

//        Delete
        studentHashMap.remove(1003);

        displayStudents(studentHashMap);

    }

    private static void displayStudents(HashSet<Student> students) {
        System.out.println("\n_____All Students Info______");
        students.forEach(s -> System.out.println(s.getRollNo() + " - " + s.getName() + " - " + s.getAddress() + " - " + s.getPhoneNo()));
    }

    private static void displayStudents(HashMap<Integer, Student> students) {
        System.out.println("\n_____All Students Info______");
        students.values().forEach(s -> System.out.println(s.getRollNo() + " - " + s.getName() + " - " + s.getAddress() + " - " + s.getPhoneNo()));
    }
}
