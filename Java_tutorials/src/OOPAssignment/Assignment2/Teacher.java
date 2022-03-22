package OOPAssignment.Assignment2;

import java.util.Scanner;

public class Teacher extends Person {
    private String position;
    private String department;
    private int salary;

    public Teacher(String position, String department, int salary) {
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public Teacher(String name, String nrcno, String address, String phone, String position, String department, int salary) {
        super(name, nrcno, address, phone);
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void promote(String position, int salary) {
        setPosition(position);
        setSalary(salary);
    }

    public void transfer(String department) {
        setDepartment(department);
    }

    public void showTeacherInfo() {
        showInfo();
        System.out.println("Position: " + position);
        System.out.println("Deparment: " + department);
        System.out.println("Salary: " + salary);
    }
}
