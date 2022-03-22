package OOPAssignment.Assignment2;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("John", "12/BaTaHta(N)12345678", "Yangon", "0912345678", "Teacher", "Teaching", 300000);

        teacher.showInfo();

        teacher.showIdentificationInfo();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter new position: ");
        String position = input.nextLine();

        System.out.print("Enter new salary: ");
        int salary = Integer.parseInt(input.nextLine());

        teacher.promote(position, salary);

        System.out.print("Enter new department: ");
        String department = input.nextLine();
        teacher.transfer(department);

        input.close();

        teacher.showTeacherInfo();
    }
}
