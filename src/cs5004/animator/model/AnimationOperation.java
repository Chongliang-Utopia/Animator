package cs5004.animator.model;

import java.util.List;
import java.util.Objects;
import java.util.Arrays;

public class AnimationOperation extends AbstractAnimation {
  private final Position2D startPos;
  private final Position2D endPos;
  private final ColorType startColor;
  private final ColorType endColor;
  private final int[] startDimension;
  private final int[] endDimension;

  /**
   * Constructor an abstract animation object.
   * @param name The name of the shape
   * @param type Type of the shape
   * @param t1   The start time of this transformation
   * @param x1   The initial x-position of the shape
   * @param y1   The initial y-position of the shape
   * @param w1   The initial width of the shape
   * @param h1   The initial height of the shape
   * @param r1   The initial red color-value of the shape
   * @param g1   The initial green color-value of the shape
   * @param b1   The initial blue color-value of the shape
   * @param t2   The end time of this transformation
   * @param x2   The final x-position of the shape
   * @param y2   The final y-position of the shape
   * @param w2   The final width of the shape
   * @param h2   The final height of the shape
   * @param r2   The final red color-value of the shape
   * @param g2   The final green color-value of the shape
   * @param b2   The final blue color-value of the shape
   */
  public AnimationOperation(String name, ShapeType type, int t1, int x1, int y1,
                            int w1, int h1, int r1, int g1, int b1,
                            int t2, int x2, int y2,
                            int w2, int h2, int r2, int g2, int b2) {
    super(name, type, t1, t2);
    this.startPos = new Position2D(x1, y1);
    this.endPos = new Position2D(x2, y2);
    this.startColor = new ColorType(r1, g1, b1);
    this.endColor = new ColorType(r2, g2, b2);
    this.startDimension = new int[] {w1, h1};
    this.endDimension = new int[] {w2, h2};
  }

  /**
   * Return a list of start position and end position of the animation.
   *
   * @return a list of start position and end position of the animation
   */
  @Override
  public List<Position2D> getPos() {
    return List.of(startPos, endPos);
  }

  /**
   * Return a list of start color and end color of the animation.
   *
   * @return a list of start color and end color of the animation
   */
  @Override
  public List<ColorType> getColor() {
    return List.of(startColor, endColor);
  }

  /**
   * Return a list of start dimension and end dimension of the animation.
   *
   * @return a list of start dimension and end dimension of the animation
   */
  @Override
  public List<int[]> getDimension() {
    return List.of(startDimension, endDimension);
  }

  /**
   * Abstract method to run the animation, which returns an updated abstract shape.
   *
   * @param curTime current time
   * @return an updated abstract shape
   * @throws IllegalArgumentException if an updated shape cannot be generated
   */
  @Override
  public IShape runAnimation(int curTime) throws IllegalArgumentException {
    if (curTime < startTime || curTime > endTime) {
      throw new IllegalArgumentException("Invalid time");
    }
    int r = (int)calculateState(
        startColor.getRed(), endColor.getRed(), curTime);
    int g = (int)calculateState(
        startColor.getGreen(), endColor.getGreen(), curTime);
    int b = (int)calculateState(
        startColor.getBlue(), endColor.getBlue(), curTime);
    ColorType color = new ColorType(r, g, b);
    double width = calculateState(startDimension[0], endDimension[0], curTime);
    double height = calculateState(startDimension[1], endDimension[1], curTime);
    double x = calculateState(startPos.getX(), endPos.getX(), curTime);
    double y = calculateState(startPos.getY(), endPos.getY(), curTime);
    Position2D pos = new Position2D(x, y);
    return ShapeFactory.buildShape(shapeName, shapeType, color, pos,
        width, height, startTime, endTime);
  }

  /**
   * Return a String representation of the animation.
   *
   * @return a String representation of the animation
   */
  @Override
  public String toString() {
    String ret = "";
    if (!startPos.equals(endPos)) {
      ret += ("Shape " + this.getShapeName() + " move from "
          + new Position2D(startPos.getX(), startPos.getY()).toString()
        + " to " + new Position2D(endPos.getX(), endPos.getY()).toString()
        + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n");
    }
    if (!Arrays.equals(startDimension, endDimension)) {
      ret += ("Shape " + this.getShapeName() + " scales from Width: " + startDimension[0]
          + " Height: " + startDimension[1]
          + " to Width: " + endDimension[0]
          + " Height: " + endDimension[1]
          + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n");
    }
    if (!startColor.equals(endColor)) {
      ret += ("Shape " + this.getShapeName() + " changes color from "
          + new ColorType(startColor.getRed(), startColor.getGreen(), startColor.getBlue())
          + " to "
          + new ColorType(endColor.getRed(), endColor.getGreen(), endColor.getBlue())
          + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n");
    }
    return ret;
  }

  /**
   * Return if two animations are equal.
   *
   * @param o another object
   * @return if two animations are equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof AnimationOperation)) {
      return false;
    }

    AnimationOperation that = (AnimationOperation) o;

    return this.shapeName.equals(that.shapeName)
        && this.startTime == that.startTime
        && this.endTime == that.endTime
        && Arrays.equals(startDimension, that.startDimension)
        && Arrays.equals(endDimension, that.endDimension)
        && this.startColor.equals(that.startColor)
        && this.endColor.equals(that.endColor)
        && this.startPos.equals(that.startPos)
        && this.endPos.equals(that.endPos);
  }

  /**
   * Return the hashcode.
   *
   * @return the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(shapeName, startTime, endTime,
        startDimension, endDimension, startColor, endColor, startPos, endPos);
  }
}
