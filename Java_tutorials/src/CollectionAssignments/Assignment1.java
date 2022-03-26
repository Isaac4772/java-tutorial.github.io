package CollectionAssignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Assignment1 {
    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>(List.of("John", "Isaac", "Kevin", "Mary", "Keller", "Sterling", "King"));

        showList(studentNames);

        Collections.sort(studentNames);

        showList(studentNames);

        int index = Collections.binarySearch(studentNames, "Isaac");
        System.out.println("Position: " + index);

        String newStudent = "Harry";
        if (!studentNames.contains(newStudent))
            studentNames.add(newStudent);
        else
            System.out.println(newStudent + " is already in the class");

        showList(studentNames);
        studentNames.stream().filter(s -> s.startsWith("K")).forEach(s -> System.out.println(s));
        studentNames.removeIf(s -> s.endsWith("g"));

        showList(studentNames);

        studentNames.clear();

        showList(studentNames);
    }

    private static void showList(ArrayList<String> studentNames) {
        System.out.println(studentNames.toString());
    }
}
