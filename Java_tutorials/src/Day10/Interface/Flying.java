package Day10.Interface;

public interface Flying {

	void fly();

}

class Bird implements Flying {

	public void fly() {
		System.out.println("Birds fly with wings");
	}
}

class Airplane implements Flying {

	public void fly() {
		System.out.println("Airplanes fly with engines");
	}
}

class Human implements Flying {

	public void fly() {
		System.out.println("Humans fly with parachute");
	}
}
