package OOPAssignment.Assignment3;

public class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(int length, int width, String color) {
        super.setColor(color);
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }

    public void displayArea() {
        System.out.println("Area Of the rectangle: " + area());
    }
}
