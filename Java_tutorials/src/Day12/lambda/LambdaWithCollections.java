package Day12.lambda;

import java.util.List;

public class LambdaWithCollections {

	public static void main(String[] args) {

		List<String> langauges = List.of("HTML", "CSS", "Java", "PHP");

		langauges.forEach(s -> System.out.println(s));

		System.out.println("---------");

		langauges.forEach(lang -> {
			if (lang.contains("Java"))
				System.out.println(lang);
		});
	}

}
