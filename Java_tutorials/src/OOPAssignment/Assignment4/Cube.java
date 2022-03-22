package OOPAssignment.Assignment4;

public class Cube implements Shape {
    private int x;

    public Cube(int x) {
        this.x = x;
    }

    @Override
    public double area() {
        return 6 * x * x;
    }

    @Override
    public double volume() {
        return x * x * x;
    }

    public void displayArea() {
        System.out.println("Cube Area: " + area());
    }

    public void displayVolume() {
        System.out.println("Cube Volume: " + volume());
    }
}
