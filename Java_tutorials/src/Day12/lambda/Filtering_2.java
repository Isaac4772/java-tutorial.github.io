package Day12.lambda;

import java.util.stream.Stream;

public class Filtering_2 {

	public static void main(String[] args) {
		Stream<Integer> numbers = Stream.iterate(1, i -> i + 1).limit(15);

		numbers.filter(n -> n > 4 && n <= 10).filter(n -> n % 2 == 0).forEach(System.out::println);
	}
}
