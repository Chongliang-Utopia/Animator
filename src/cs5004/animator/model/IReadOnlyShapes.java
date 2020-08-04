package cs5004.animator.model;

/**
 * This interface IReadOnlyShapes is created to prevent mutation of the original shapes.
 */
public interface IReadOnlyShapes {

  /**
   * Get the name of the Shape.
   *
   * @return the name of the shape object
   */
  String getName();

  /**
   * Get the color of the shape.
   *
   * @return the color of the shape
   */
  ColorType getColor();

  /**
   * Get the current position of the shape.
   *
   * @return the position of the shape
   */
  Position2D getPosition();

  /**
   * Get the appear time of the shape.
   *
   * @return the appear time of the shape
   */
  int getAppearTime();

  /**
   * Get the disappear time of the shape.
   *
   * @return the disappear time of the shape
   */
  int getDisappearTime();

  /**
   * Get the type of the shape.
   *
   * @return the type of the shape
   */
  ShapeType getType();

  /**
   * Get whether to display the shape.
   *
   * @return true to display the object, otherwise false
   */
  boolean getDisplay();

  /**
   * Abstract method to get the horizontal length of the shape, such as width or xRadius.
   *
   * @return the horizontal length of the shape
   */
  double getWidth();

  /**
   * Abstract method to get the vertical length of the shape, such as height or yRadius.
   *
   * @return the vertical length of the shape
   */
  double getHeight();

  /**
   * Return String representation of the shape details.
   * @return string representation of the shape details
   */
  String textRender();

  /**
   * Return String representation of the shape position and size.
   * @return string representation of the shape position and size
   */

  String stringPosSize();
}
