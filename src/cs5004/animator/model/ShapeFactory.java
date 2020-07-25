package cs5004.animator.model;

public class ShapeFactory {
    public static AbstractShape buildShape(ShapeType shapeType, String name, ShapeType type,
                                  ColorType color, Position2D position, double width,
                                  double height, int appears, int disappears) {
        AbstractShape result = null;
        switch (shapeType) {
            case OVAL:
                result = new Oval(name, type, color, position, width, height,
                        appears, disappears);
                break;
            case RECTANGLE:
                result = new Rectangle(name, type, color, position, width, height,
                        appears, disappears);
                break;
            default: throw new IllegalArgumentException("Invalid animation to run");
        }
        return result;
    }
}
