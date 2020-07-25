package cs5004.animator.model;

/**
 * A shapeFactory class to generate a abstract shape according the given parameters.
 */
public class ShapeFactory {
    /**
     * A static method to generate an abstract shape according the given parameters
     * @param name the name of the shape
     * @param type the type of the shape to generate
     * @param color the color of the shape
     * @param position the position of the shape
     * @param width the width of the shape
     * @param height the height of the shape
     * @param appears the appear time of the shape
     * @param disappears the disappear time of the shape
     * @return an abstract shape according the given parameters
     * @throws IllegalArgumentException if the given shapeType is not supported
     */
    public static AbstractShape buildShape(String name, ShapeType type,
                                  ColorType color, Position2D position, double width,
                                  double height, int appears, int disappears)
                                                    throws IllegalArgumentException{
        AbstractShape result = null;
        switch (type) {
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
