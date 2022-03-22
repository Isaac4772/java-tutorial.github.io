package OOPAssignment.Assignment4;

public class Circle implements Shape {

    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double volume() {
        return 0;
    }

    public void displayArea() {
        System.out.println("Circle Area: " + area());
    }

    public void displayVolume() {
        System.out.println("Circle Volume: " + volume());
    }
}
