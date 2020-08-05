package cs5004.animator.model;

/**
 * Represent an abstract class of shapes in the model, which contains all common attributes
 * of all shapes and methods to manipulate shapes. It can provide some useful details of shape
 * for others to retrieve.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected ShapeType type;
  protected ColorType color;
  protected Position2D position;
  protected boolean display;
  protected int appearTime;
  protected int disappearTime;

  /**
   * Construct an AbstractShape object which contains details of the shape, such as name, type,
   * color, position, and appear/disappear time.
   *
   * @param name       the name of the shape
   * @param type       the type of the shape
   * @param color      the color of this shape
   * @param position   the position of this shape
   * @param appears    the appears time of the shape
   * @param disappears the disappears time of the shape
   * @throws IllegalArgumentException if the given parameters are invalid for the shape
   */
  public AbstractShape(String name, ShapeType type, ColorType color, Position2D position,
                       int appears, int disappears) throws IllegalArgumentException {
    if (appears < 0 || disappears < appears) {
      throw new IllegalArgumentException("Not valid appear or disappear time");
    }
    if (name == null || type == null || position == null || color == null) {
      throw new IllegalArgumentException("Invalid given name, type, color or position of shape");
    }
    this.name = name;
    this.type = type;
    this.color = color;
    this.position = position;
    this.appearTime = appears;
    this.disappearTime = disappears;
    this.display = true;
  }

  /**
   * A default constructor when only the name of the shape is given.
   * @param name name of the shape
   */
  public AbstractShape(String name) {
    this.name = name;
    this.color = new ColorType(0, 0, 0);
    this.position = new Position2D(0, 0);
    this.appearTime = Integer.MAX_VALUE;
    this.disappearTime = Integer.MIN_VALUE;
    this.display = true;
  }

  /**
   * Get the name of the Shape.
   *
   * @return the name of the shape object
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Set the shape's color to the given color.
   *
   * @param color the color of the shape
   */
  public void setColor(ColorType color) {
    this.color = color;
  }

  /**
   * Set the shape's appearTime to the given color.
   *
   * @param appearTime the appearTime of the shape
   */
  public void setAppearTime(int appearTime) {
    this.appearTime = appearTime;
  }

  /**
   * Set the shape's appearTime to the given color.
   *
   * @param disappearTime the appearTime of the shape
   */
  public void setDisappearTime(int disappearTime) {
    this.disappearTime = disappearTime;
  }

  /**
   * Get the color of the shape.
   *
   * @return the color of the shape
   */
  @Override
  public ColorType getColor() {
    return color;
  }

  /**
   * Get the current position of the shape.
   *
   * @return the position of the shape
   */
  @Override
  public Position2D getPosition() {
    return position;
  }

  /**
   * Get the appear time of the shape.
   *
   * @return the appear time of the shape
   */
  @Override
  public int getAppearTime() {
    return appearTime;
  }

  /**
   * Get the disappear time of the shape.
   *
   * @return the disappear time of the shape
   */
  @Override
  public int getDisappearTime() {
    return disappearTime;
  }

  /**
   * Get the type of the shape.
   *
   * @return the type of the shape
   */
  @Override
  public ShapeType getType() {
    return type;
  }

  /**
   * Set whether to display the shape.
   *
   * @param display the display mode of the shape object, if it is true, display the object
   */
  public void setDisplay(boolean display) {
    this.display = display;
  }

  /**
   * Get whether to display the shape.
   *
   * @return true to display the object, otherwise false
   */
  @Override
  public boolean getDisplay() {
    return this.display;
  }

  /**
   * Abstract method to get the horizontal length of the shape, such as width or xRadius.
   * @return the horizontal length of the shape
   */
  @Override
  public abstract double getWidth();

  /**
   * Abstract method to get the vertical length of the shape, such as height or yRadius.
   * @return the vertical length of the shape
   */
  @Override
  public abstract double getHeight();

  /**
   * Return String representation of the shape details.
   * @return string representation of the shape details
   */
  @Override
  public String textRender() {
    return "";
  }


//  /**
//   * Return String representation of the shape position and size.
//   * @return string representation of the shape position and size
//   */
//  @Override
//  public String stringPosSize() {
//    return "";
//  }

}
