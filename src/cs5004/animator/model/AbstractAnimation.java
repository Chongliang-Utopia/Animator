package cs5004.animator.model;

import java.util.List;

/**
 * Represent an abstract class of animation in the model. It contains the common attributes of
 * common attributes and methods, including the animation type, shape name, start time and end time.
 * It includes common getter of these attributes, as well as a calculateState to calculate the
 * current state according to the current time.
 */
public abstract class AbstractAnimation implements IAnimation{
  protected String shapeName;
  protected int startTime;
  protected int endTime;
  protected ShapeType shapeType;

  /**
   * Constructor an abstract animation object.
   *
   * @param shapeName     name of the shape
   * @param shapeType     shapeType
   * @param startTime     start time of the animation
   * @param endTime       end time of the animation
   * @throws IllegalArgumentException if the start time or end time is invalid
   */
  public AbstractAnimation(String shapeName, ShapeType shapeType,
                           int startTime, int endTime)
      throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0 || startTime > endTime) {
      throw new IllegalArgumentException("Invalid time frame for the animation.");
    }
    this.shapeName = shapeName;
    this.startTime = startTime;
    this.endTime = endTime;
    this.shapeType = shapeType;
  }

  /**
   * Return the name of the shape in the animation.
   *
   * @return the name of the shape in the animation
   */
  public String getShapeName() {
    return this.shapeName;
  }

  /**
   * Return the start time of the animation.
   *
   * @return the start time of the animation
   */
  public int getStartTime() {
    return this.startTime;
  }

  /**
   * Return the end time of the animation.
   *
   * @return the end time of the animation
   */
  public int getEndTime() {
    return this.endTime;
  }

  /**
   * Return a list of start position and end position of the animation.
   *
   * @return a list of start position and end position of the animation
   */
  public abstract List<Position2D> getPos();

  /**
   * Return a list of start color and end color of the animation.
   *
   * @return a list of start color and end color of the animation
   */
  public abstract List<ColorType> getColor();

  /**
   * Return a list of start dimension and end dimension of the animation.
   *
   * @return a list of start dimension and end dimension of the animation
   */
  public abstract List<int[]> getDimension();


  /**
   * Abstract method to run the animation, which returns an updated abstract shape.
   *
   * @param curTime current time
   * @return an updated abstract shape
   * @throws IllegalArgumentException if an updated shape cannot be generated
   */
  public abstract AbstractShape runAnimation(int curTime) throws
      IllegalArgumentException;

  /**
   * Calculate the current state according the start state, end state in the given time. The state
   * could represent any value that the shape has, such as its width, height, RGB individual values,
   * etc.
   *
   * @param startState start state
   * @param endState   end state
   * @param curTime    current time
   * @return current state according the start state, end state in the given time
   */
  protected double calculateState(double startState, double endState, int curTime) {
    if (curTime <= startTime) {
      return startState;
    } else if (curTime >= endTime) {
      return endState;
    }
    return startState + (endState - startState) / (endTime - startTime) * (curTime - startTime);
  }

}

