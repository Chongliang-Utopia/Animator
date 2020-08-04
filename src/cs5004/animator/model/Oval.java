package cs5004.animator.model;

import java.util.Objects;

/**
 * Represent the class of Oval and extends the AbstractShape. Rectangle also has xRadius and
 * yRadius.
 */
public class Oval extends AbstractShape {
  private double xRadius;
  private double yRadius;

  /**
   * Construct an Oval object which contains details of the shape, such as name, type, color,
   * position, xRadius/yRadius, and appear/disappear time.
   *
   * @param name       the name of the oval
   * @param type       the type of the oval
   * @param color      the color of this oval
   * @param position   the position of this oval
   * @param xRadius    the x radius of the oval
   * @param yRadius    the y radius of the oval
   * @param appears    the appears time of the shape
   * @param disappears the disappears time of the shape
   * @throws IllegalArgumentException if the given parameters are invalid for the shape
   */
  public Oval(String name, ShapeType type, ColorType color, Position2D position, double xRadius,
              double yRadius, int appears, int disappears) throws IllegalArgumentException {
    super(name, type, color, position, appears, disappears);
    if (type != ShapeType.OVAL) {
      throw new IllegalArgumentException("Invalid given type of shape");
    }
    if (xRadius <= 0 || yRadius <= 0) {
      throw new IllegalArgumentException("Invalid given width or height");
    }
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * A default constructor when only the name of the shape is given.
   * @param name name of the shape
   */
  public Oval(String name) {
    super(name);
    this.xRadius = 1;
    this.yRadius = 1;
    this.type = ShapeType.OVAL;
  }

  /**
   * Abstract method to get the horizontal length of the shape, such as width or xRadius.
   *
   * @return the horizontal length of the shape
   */
  @Override
  public double getWidth() {
    return this.xRadius;
  }

  /**
   * Abstract method to get the vertical length of the shape, such as height or yRadius.
   *
   * @return the vertical length of the shape
   */
  @Override
  public double getHeight() {
    return this.yRadius;
  }

  @Override
  public boolean equals(Object o) {
    // Fast path for pointer equality:
    if (this == o) {
      return true;
    }

    // If o isn't the right class then it can't be equal:
    if (! (o instanceof AbstractShape)) {
      return false;
    }

    // The successful instanceof check means our cast will succeed:
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
            && this.xRadius == that.getWidth()
            && this.yRadius == that.getHeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, color.getRed(), color.getGreen(), color.getBlue(),
            position.getX(), position.getY(), appearTime, disappearTime, display, xRadius, yRadius);
  }


  /**
   * Return String representation of the oval details. e.g.
   * Name: C
   * Type: oval
   * Center: (500.0,100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)
   * Appears at t=6
   * Disappears at t=100
   *
   * @return string representation of the oval information
   */
  @Override
  public String textRender() {
    String text = "";
    text += "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n"
            + "Center: " + this.position.toString()
            + String.format(", X radius: %.1f, Y radius: %.1f, ", this.xRadius, this.yRadius)
            + "Color: " + this.color.toString() + "\n"
            + "Appears at t=" + this.appearTime + "\n" + "Disappears at t=" + this.disappearTime
            + "\n";
    return text;
  }

//  /**
//   * Return String representation of the oval position and size. e.g.
//   * center at (500.0,100.0), radius: 60, radius: 30
//   *
//   * @return string representation of the oval information
//   */
//  @Override
//  public String stringPosSize() {
//    String text = "";
//    text += "center at " + this.position.toString()
//            + String.format(", radius: %.0f and %.0f\n", this.xRadius, this.yRadius);
//    return text;
//  }
}
