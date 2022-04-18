package Day16.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;

public class ReadAllLines {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("src\\Day16\\fileio\\employee.txt");
        List<String> lines = Files.readAllLines(path);

        lines.forEach(System.out::println);
        List<Employee> employeeList = lines.stream()
                .map(Employee::getEmployeeFromLine).toList();

        employeeList.forEach(emp -> {
                    System.out.println("Id: " + emp.getId());
                    System.out.println("Name: " + emp.getName());
                    System.out.println("Address: " + emp.getAddress());
                }
        );
    }
}

class Employee {
    private int id;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static Employee getEmployeeFromLine(String line) {
        Employee employee = new Employee();
        String[] data = line.split("\t");
        employee.setId(Integer.parseInt(data[0]));
        employee.setName(data[1]);
        employee.setAddress(data[2]);
        return employee;
    }
}
