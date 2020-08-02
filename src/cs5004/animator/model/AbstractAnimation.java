package cs5004.animator.model;

/**
 * Represent an abstract class of animation in the model. It contains the common attributes of
 * common attributes and methods, including the animation type, shape name, start time and end time.
 * It includes common getter of these attributes, as well as a calculateState to calculate the
 * current state according to the current time.
 */
public abstract class AbstractAnimation {
  protected AnimationType animationType;
  protected String shapeName;
  protected AbstractShape originalShape;
  protected int startTime;
  protected int endTime;

  /**
   * Constructor an abstract animation object.
   *
   * @param animationType type of the animation
   * @param shapeName     name of the shape
   * @param startTime     start time of the animation
   * @param endTime       end time of the animation
   * @param originalShape the original shape to transform
   * @throws IllegalArgumentException if the start time or end time is invalid
   */
  public AbstractAnimation(AnimationType animationType, String shapeName,
                           int startTime, int endTime, AbstractShape originalShape)
      throws IllegalArgumentException {
    if (startTime < 0 || endTime < 0 || startTime >= endTime) {
      throw new IllegalArgumentException("Invalid time frame for the animation.");
    }
    this.animationType = animationType;
    this.shapeName = shapeName;
    this.startTime = startTime;
    this.endTime = endTime;
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
   * Return the animation type.
   *
   * @return the animation type
   */
  public AnimationType getAnimationType() {
    return this.animationType;
  }

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

