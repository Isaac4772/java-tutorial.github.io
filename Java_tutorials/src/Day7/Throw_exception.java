package Day7;

public class Throw_exception {

	public static void main(String[] args) {
		try {
			int[] numbers = {100,200,300,400};
			int index = 5;
			if(index >= numbers.length) 
				throw new ArrayIndexOutOfBoundsException("Out of Range");
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.err.println(e.getMessage());
		}
		

	}

}
