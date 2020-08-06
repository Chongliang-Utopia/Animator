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
   * @param name       the name of the rectangle
   * @param type       the type of the rectangle
   * @param color      the color of this rectangle
   * @param position   the position of this rectangle, represented by its left bottom corner
   * @param width      the width of this rectangle
   * @param height     the height of this rectangle
   * @param appears    the appears time of the rectangle
   * @param disappears the disappears time of the rectangle
   * @throws IllegalArgumentException if the given parameters are invalid for the rectangle
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
   * A default constructor when only the name of the shape is given.
   *
   * @param name name of the shape
   */
  public Rectangle(String name) {
    super(name);
    this.width = 1;
    this.height = 1;
    this.type = ShapeType.RECTANGLE;
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


  /**
   * Return String representation of the rectangle details. e.g. Name: R Type: rectangle Min corner:
   * (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0) Appears at t=1 Disappears at
   * t=100
   *
   * @return string representation of the rectangle information
   */
  @Override
  public String textRender() {
    String text = "";
    text += "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n"
            + "Min corner: " + this.position.toString()
            + String.format(", Width: %.1f, Height: %.1f, ", this.width, this.height)
            + "Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.appearTime + "\n" + "Disappears at t=" + this.disappearTime
            + "\n";
    return text;
  }


}
