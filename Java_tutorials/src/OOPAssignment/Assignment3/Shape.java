package OOPAssignment.Assignment3;

public abstract class Shape {
    String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public abstract double area();
}
