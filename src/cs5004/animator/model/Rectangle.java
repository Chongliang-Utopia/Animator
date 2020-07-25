package cs5004.animator.model;

import java.util.Objects;

/**
 * Represent the class of rectangle and extends the AbstractShape. Rectangle also has width and
 * height.
 */
public class Rectangle extends AbstractShape {
  private double width;
  private double height;

  /**
   * Construct a Rectangle object which contains details of the shape, such as name, type, color,
   * position, width/height, and appear/disappear time.
   *
   * @param name       the name of the shape
   * @param type       the type of the shape
   * @param color      the color of this shape
   * @param position   the position of this shape
   * @param width      the width of this shape
   * @param height     the height of this shape
   * @param appears    the appears time of the shape
   * @param disappears the disappears time of the shape
   * @throws IllegalArgumentException if the given parameters are invalid for the shape
   */
  public Rectangle(String name, ShapeType type, ColorType color, Position2D position, double width,
                   double height, int appears, int disappears) throws IllegalArgumentException {
    super(name, type, color, position, appears, disappears);
    if (type != ShapeType.RECTANGLE) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid given width or height");
    }
    this.width = width;
    this.height = height;
  }

  /**
   * Get the width of the Rectangle.
   *
   * @return width of the Rectangle
   */
  public double getWidth() {
    return width;
  }

  /**
   * Get the height of the Rectangle.
   *
   * @return the height of the Rectangle
   */
  public double getHeight() {
    return height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof AbstractShape)) {
      return false;
    }

    AbstractShape that = (AbstractShape) o;

    return this.name.equals(that.name)
            && this.type.equals(that.type)
            && this.color.getRed() == that.getColor().getRed()
            && this.color.getGreen() == that.getColor().getGreen()
            && this.color.getBlue() == that.getColor().getBlue()
            && this.position.getX() == that.getPosition().getX()
            && this.position.getY() == that.getPosition().getY()
            && this.appearTime == that.appearTime
            && this.disappearTime == that.disappearTime
            && this.display == that.display
            && this.width == that.getWidth()
            && this.height == that.getHeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, color.getRed(), color.getGreen(), color.getBlue(),
            position.getX(), position.getY(), appearTime, disappearTime, display, width, height);
  }


}
