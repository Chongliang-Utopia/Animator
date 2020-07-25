package cs5004.animator.model;

/**
 * A class represents an animation to move the shape, it extends the AbstractAnimation
 * class. It stores the start position and end position of the animation.
 * It can also call a method to run the animation and get an updated Abstract shape.
 */
public class MoveAnimation extends AbstractAnimation {
  private Position2D startPosition;
  private Position2D endPosition;

  /**
   * Construct an ChangeColorAnimation object.
   * @param animationType the type of the animation
   * @param shapeName the name of the shape
   * @param startTime the start time of the animation
   * @param endTime the end time of the animation
   * @param startPosition the start position
   * @param endPosition the end position
   * @throws IllegalArgumentException if the given animation type is invalid
   */
  public MoveAnimation(AnimationType animationType,
                       String shapeName,
                       int startTime,
                       int endTime,
                       Position2D startPosition,
                       Position2D endPosition) throws IllegalArgumentException {
    super(animationType, shapeName, startTime, endTime);
    if (animationType != AnimationType.MOVE) {
      throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
    }
    this.startPosition = startPosition;
    this.endPosition = endPosition;
  }

  /**
   * Abstract method to run the animation, which returns an updated abstract shape.
   * @param shape shape to run the animation
   * @param curTime current time
   * @return an updated abstract shape
   * @throws IllegalArgumentException if an updated shape cannot be generated
   */
  @Override
  public AbstractShape runAnimation(AbstractShape shape, int curTime)
          throws IllegalArgumentException{
    double curXCoordinate = this.calculateState(startPosition.getX(), endPosition.getX(), curTime);
    double curYCoordinate = this.calculateState(startPosition.getY(), endPosition.getY(), curTime);
    Position2D curPosition = new Position2D(curXCoordinate, curYCoordinate);
    // Create an updated shape.
    AbstractShape result = ShapeFactory.buildShape(shape.getName(), shape.getType(),
            shape.getColor(), curPosition, shape.getWidth(), shape.getHeight(),
            shape.getAppearTime(), shape.getDisappearTime());
    if (result == null) {
      throw new IllegalArgumentException("Invalid animation to run on this shape");
    }
    return result;
  }

  /**
   * Return a String representation of the animation.
   * @return a String representation of the animation
   */
  @Override
  public String toString() {
    return "Shape " + this.getShapeName() + " move from " + startPosition.toString()
            + " to " + endPosition.toString()
            + " from t=" + this.getStartTime() + " to t=" + this.getEndTime();
  }
}
