package cs5004.animator.model;

/**
 *  The IShape interface extends the IReadOnlyShapes interface to add setter methods.
 */
public interface IShape extends IReadOnlyShapes {
  /**
   * Set the shape's color to the given color.
   *
   * @param color the color of the shape
   */
  void setColor(ColorType color);

  /**
   * Set whether to display the shape.
   *
   * @param display the display mode of the shape object, if it is true, display the object
   */
  void setDisplay(boolean display);

}
