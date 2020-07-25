package cs5004.animator.model;

public class MoveAnimation extends AbstractAnimation {
  private Position2D startPosition;
  private Position2D endPosition;

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

  @Override
  public AbstractShape runAnimation(AbstractShape shape, int curTime) {
    double curXCoordinate = this.calculateState(startPosition.getX(), endPosition.getX(), curTime);
    double curYCoordinate = this.calculateState(startPosition.getY(), endPosition.getY(), curTime);
    Position2D curPosition = new Position2D();
    return shape.updateWithNewState(curPosition);
  }

  @Override
  public String toString() {
    return "Shape " + this.getShapeName() + " move from " + startPosition.toString()
            + " to " + endPosition.toString()
            + " from t=" + this.getStartTime() + " to t=" + this.getEndTime();
  }
}
