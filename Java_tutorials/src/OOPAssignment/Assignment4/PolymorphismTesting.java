package OOPAssignment.Assignment4;

public class PolymorphismTesting {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(3);
        shapes[1] = new Cube(4);
        shapes[2] = new Circle(5);

        shapes[0].displayArea();
        shapes[0].displayVolume();

        shapes[1].displayArea();
        shapes[1].displayVolume();

        shapes[2].displayArea();
        shapes[2].displayVolume();
    }
}
