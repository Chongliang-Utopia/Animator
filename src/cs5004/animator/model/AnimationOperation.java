package cs5004.animator.model;

import java.util.Objects;
import java.util.Arrays;

public class AnimationOperation extends AbstractAnimation {
  private final int[] startState;
  private final int[] endState;

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
    startState = new int[] {x1, y1, w1, h1, r1, g1, b1};
    endState = new int[] {x2, y2, w2, h2, r2, g2, b2};
  }

  @Override
  public AbstractShape runAnimation(int curTime) throws IllegalArgumentException {
    int[] curState = new int[startState.length];
    for (int i = 0; i < startState.length; i++) {
      curState[i] = (int) calculateState(startState[i], endState[i], curTime);
    }
    ColorType color = new ColorType(curState[4], curState[5], curState[6]);
    Position2D pos = new Position2D(curState[0], curState[1]);
    return ShapeFactory.buildShape(shapeName, shapeType, color, pos,
        curState[2], curState[3], startTime, endTime);
  }

  /**
   * Return a String representation of the animation.
   *
   * @return a String representation of the animation
   */
  @Override
  public String toString() {
    String ret = "";
    if (startState[0] != endState[0] || startState[1] != endState[1] ) {
      ret += ("Shape " + this.getShapeName() + " move from "
          + new Position2D(startState[0], startState[1]).toString()
        + " to " + new Position2D(endState[0], endState[1]).toString()
        + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n");
    }
    if (startState[2] != endState[2] || startState[3] != endState[3]) {
      ret += ("Shape " + this.getShapeName() + " scales from Width: " + startState[2]
          + " Height: " + startState[3]
          + " to Width: " + endState[2]
          + " Height: " + endState[3]
          + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n");
    }
    if (startState[4] != endState[4]
        || startState[5] != endState[5]
        || startState[6] != endState[6]) {
      ret += ("Shape " + this.getShapeName() + " changes color from "
          + new ColorType(startState[4], startState[5], startState[6])
          + " to "
          + new ColorType(endState[4], endState[5], endState[6])
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
        && Arrays.equals(startState, that.startState)
        && Arrays.equals(endState, that.endState);
  }

  /**
   * Return the hashcode.
   *
   * @return the hashcode
   */
  @Override
  public int hashCode() {
    return Objects.hash(shapeName, startTime, endTime,
        startState, endState);
  }
}
