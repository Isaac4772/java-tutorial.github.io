package Day12.lambda;

import java.util.List;
import java.util.stream.Collectors;

public class Filtering_1 {
	public static void main(String[] args) {
		List<Users> users = List.of(new Users("Kyaw Kyaw", "Admin"), new Users("Aung Aung", "Staff"),
				new Users("Hla Hla", "Staff"), new Users("Yuri", "Customer"), new Users("John", "Customer"));

		List<String> names = users.stream()
				.filter(u -> u.getName().contains("Aung") && u.getRole().equalsIgnoreCase("staff"))
				.map(u -> u.getName()).collect(Collectors.toList());

		System.out.println(names);
	}
}
