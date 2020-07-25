package cs5004.animator.model;

import java.util.Objects;

/**
 * Represent the class of position, which use the two decimal numbers to represent the position of
 * some objects in the 2D background of animation.
 */
public final class Position2D {
  private final double x;
  private final double y;


  /**
   * Construct one Position2D from given x and y value to make sure the 2D position in the
   * background.
   *
   * @param x the given double for represent the x position in the background
   * @param y the given double for represent the x position in the background
   */
  public Position2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x position of the Position2D class.
   *
   * @return the double to represent x coordinate position
   */
  public double getX() {
    return x;
  }

  /**
   * Get the y position of the Position2D class.
   *
   * @return the double to represent y coordinate position
   */
  public double getY() {
    return y;
  }


  /**
   * Construct a copy of the given position.
   *
   * @param position the given Position2D
   */
  public Position2D(Position2D position) {
    this(position.x, position.y);
  }

  /**
   * Return the string format of the position as the following example: (200.0, 200.0).
   *
   * @return the string to represent the position of shape
   */

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

  /**
   * Determine if the Position2D object equals to the given object based on the x and y.
   *
   * @param a the given Position2D object
   * @return true if the given object is a Position2D object and has the same x, y
   */
  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }


  /**
   * Use the x and y as the hashcode.
   *
   * @return hashcode of the Position2D
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}