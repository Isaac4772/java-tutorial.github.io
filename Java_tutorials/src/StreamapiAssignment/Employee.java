package StreamapiAssignment;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.LocalDate.*;

public class Employee {
    private String name;
    private String city;
    private String department;
    private int salary;
    private LocalDate birthday;

    static ArrayList<Employee> employees = new ArrayList<>(
            Arrays.asList(
                    new Employee("Htet Htet", "Yangon", "Electronics", 900000, parse("1991-10-16")),
                    new Employee("Cherry", "Yangon", "Electronics", 820000, parse("1994-08-14")),
                    new Employee("Kyaw Kyaw", "Yangon", "Electronics", 780000, parse("1988-09-02")),
                    new Employee("Aung Aung", "Mandalay", "IT", 600000, parse("1995-01-11")),
                    new Employee("Jeon", "Mandalay", "IT", 600000, parse("1997-09-01")),
                    new Employee("Hsu Hsu", "Pyin Oo Lwin", "IT", 920000, parse("1994-12-10")),
                    new Employee("Aye Aye", "Yangon", "HR", 500000, parse("1992-10-10")),
                    new Employee("Gay Gay", "Taung Gyi", "HR", 400000, parse("1996-05-12")),
                    new Employee("Phway Phway", "Monywa", "HR", 300000, parse("1995-09-03")),
                    new Employee("Ko Ko", "Monywa", "IT", 500000, parse("1992-11-11"))
            )
    );

    public Employee(String name, String city, String department, int salary, LocalDate birthday) {
        this.name = name;
        this.city = city;
        this.department = department;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    private static int findMinimumSalary() {
        return employees.stream()
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow();
    }

    private static Employee findYoungestEmp() {
        return employees.stream()
                .min(Comparator.comparing(Employee::getBirthday))
                .orElseThrow();
    }

    private static int getTotalSalary() {
        return employees.stream()
                .mapToInt(Employee::getSalary).sum();
    }

    private static void fetchMaxSalaries(int count) {
        employees.stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .limit(count)
                .forEach(System.out::println);
    }

    private static double getAverageSalary(String department) {
        return employees.stream()
                .filter(s -> s.getDepartment().equalsIgnoreCase(department))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElseThrow();
    }

    public static void main(String[] args) {
//        Minimum Salary
        int minSalary = findMinimumSalary();
        System.out.println("\n1. Minimum salary: " + minSalary);

//        Youngest Employee
        Employee youngestEmp = findYoungestEmp();
        System.out.println("\n2. Youngest Employee: " + youngestEmp.getName());

//        Count
        long count = employees.stream().filter(s -> s.getBirthday().isEqual(LocalDate.parse("1995-02-12")) || s.getBirthday().isAfter(LocalDate.parse("1995-02-12")))
                .count();
        System.out.println("\n3. Numbers of employees whose birthday is '1995-02-12' or greater: " + count);

//        Total Salary
        int total = getTotalSalary();
        System.out.println("\n4. Total Salary: " + total);

//        Fetch Top 3 max salaries
        System.out.println("\n5. _____Top 3 max salaries______");
        fetchMaxSalaries(3);

//        Average salary of "HR" department
        String department = "HR";
        double avgSalary = getAverageSalary(department);
        System.out.println("\n6. Average salary of " + department + " department: " + avgSalary);

//        Employee with loweset salary
        Employee empWithLowestSal = employees.stream()
                .min(Comparator.comparing(Employee::getSalary))
                .orElseThrow();

        System.out.println("\n7. Employee with lowest salary: " + empWithLowestSal.getName());

//        The highest salary of each city
        Map<String, Employee> minSalOfEachCities = employees.stream().collect(
                Collectors.toMap(Employee::getCity, Function.identity(), (Employee d1, Employee d2) -> d1.getSalary() > d2.getSalary() ? d1 : d2));

        System.out.println("\n8. _____The Highest salary of each city_____");
        minSalOfEachCities.forEach((s, d) -> System.out.println(s + "- " + d.getSalary()));


//        List of employees who get the same salary
        Map<Integer, List<Employee>> groupBySalary = employees.stream()
                .collect(Collectors.groupingBy(Employee::getSalary));

        System.out.println("\n9. _____List of employees who get the same salary_____");
        groupBySalary.forEach((k, v) -> {
            System.out.println("\n" + k + " ks.");
            v.forEach(a -> System.out.println("\t" + a.getName() + "(" + a.getCity() + ")"));
        });

//        List of employee names in each department
        Map<String, List<Employee>> groupByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("\n10. _____List of employee names in each department_____");
        groupByDepartment.forEach((k, v) -> {
            System.out.println("\n " + k);
            v.forEach(a -> System.out.println("\t" + a.getName()));
        });
    }

}
