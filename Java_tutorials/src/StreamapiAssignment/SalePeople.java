package StreamapiAssignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalePeople {
    private String name;
    private String city;
    private float comm;

    public SalePeople(String name, String city, float comm) {
        this.name = name;
        this.city = city;
        this.comm = comm;
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

    public float getComm() {
        return comm;
    }

    public void setComm(float comm) {
        this.comm = comm;
    }

    public static void main(String[] args) {
        ArrayList<SalePeople> salePeople = new ArrayList<SalePeople>(
                List.of(new SalePeople("Peel", "London", 0.12f),
                        new SalePeople("Serres", "San Jose", 0.13f),
                        new SalePeople("Motika", "London", 0.11f),
                        new SalePeople("Rifkin", "Barelona", 0.15f),
                        new SalePeople("Axelrod", "New York", 0.10f)
                ));

        System.out.println("\n_____Salepeople who have the commision above 1.0_____\n");
        salePeople.stream()
                .filter(s -> s.getComm() > 0.10f)
                .forEach(s -> System.out.println(s.getName() + " - " + s.getCity()));

        System.out.println("\n____Salesperson details not having commission .10, .13, .15");
        salePeople.stream()
                .filter(s -> s.getComm() != .10f && s.getComm() != .13f && s.getComm() != .15f)
                .forEach(SalePeople::display);

        System.out.println("\n_____Different City Names_____");
        salePeople.stream()
                .collect(Collectors.groupingBy(SalePeople::getCity))
                .values().stream()
                .map(e -> e.get(0))
                .forEach(s -> System.out.println(s.getCity()));

        System.out.println("\n_____The top of (salepeople 3) records_____");
        salePeople.stream()
                .limit(3)
                .forEach(SalePeople::display);

        System.out.println("\n_____Salepeople Who Lives in 'Rome'_____");
        salePeople.stream()
                .filter(s -> s.getCity().equalsIgnoreCase("Rome"))
                .forEach(SalePeople::display);

        System.out.println("\n_____Number of salepeople who lives in London_____");
        System.out.println(salePeople.stream()
                .filter(s -> s.getCity().equalsIgnoreCase("London"))
                .count());

        System.out.println("\n_____Descending Comm Order_____");
        salePeople.stream()
                .sorted(Comparator.comparing(SalePeople::getComm).reversed())
                .toList().forEach(SalePeople::display);
    }

    private static void display(SalePeople s) {
        System.out.println(s.getName() + " - " + s.getCity() + " - " + s.getComm());
    }

}
