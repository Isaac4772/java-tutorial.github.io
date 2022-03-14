package Day13.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Map_Testing {

	public static void main(String[] args) {
		HashMap<String, String> foods = new HashMap<>();
		foods.put("Orange", "Fruit");
		foods.put("Apple", "Fruit");

		Map<String, String> anotherMap = Map.of("Mango", "Fruit", "Potatoes", "Vegetable");

		foods.putAll(anotherMap);

		System.out.println(foods);

		Map<String, String> anotherMap2 = Map.ofEntries(Map.entry("Coffee", "Juice"), Map.entry("LemonTea", "Juice"));
		foods.putAll(anotherMap2);

		foods.forEach((k, v) -> System.out.println(k + ": " + v));

		System.out.println("Coffee?: " + foods.containsKey("Coffee"));
		System.out.println("Snack?: " + foods.containsValue("Snack"));

		Set<String> keys = foods.keySet();
		System.out.println("Keys: " + keys);

		Collection<String> values = foods.values();
		System.out.println("Values: " + values);
		
		
	}

}
