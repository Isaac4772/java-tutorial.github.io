package OOPAssignment.Assignment1;

import java.util.Scanner;

public class Testing {
    public static void main(String[] args) {
        Student[] data = new Student[4];

//        Init Data
        initializeData(data);
//        System.out.println(Arrays.toString(data));

//        display
        Display(data);

//        search
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student id to search: ");
        int id = Integer.parseInt(sc.nextLine());
        searchStudent(id, data);
        System.out.println();

//        Find Maximum mark
        findMax(data);
        System.out.println();

//        Find average mark
        findAverage(data);
        sc.close();

    }

    private static void findAverage(Student[] data) {
        var total = 0;
        for (var std : data) {
            total += std.getMark();
        }
        System.out.println("Average Mark: " + total / data.length);
    }

    private static void findMax(Student[] data) {
        var max = 0;
        var index = -1;
        for (var i = 0; i < data.length; i++) {
            if (data[i].getMark() > max) {
                max = data[i].getMark();
                index = i;
            }
        }
        data[index].display();
    }

    private static void searchStudent(int id, Student[] data) {
        boolean found = false;
        for (var stud : data) {
            if (stud.getStudentId() == id) {
                stud.display();
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println(id + " is not found");
    }

    private static void Display(Student[] data) {
        for (var std : data) {
            std.display();
            System.out.println("--------------");
        }
    }

    private static void initializeData(Student[] data) {

        Scanner input = new Scanner(System.in);
        for (var i = 0; i < data.length; i++) {
            System.out.print("Enter Id: ");
            var id = Integer.parseInt(input.nextLine());
            System.out.print("Enter Name: ");
            var name = input.nextLine();
            System.out.print("Enter mark: ");
            var mark = Integer.parseInt(input.nextLine());
            data[i] = new Student(id, name, mark);
        }

    }

}

