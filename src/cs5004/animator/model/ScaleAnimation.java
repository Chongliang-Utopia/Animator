//package cs5004.animator.model;
//
//import java.util.List;
//import java.util.Objects;
//
///**
// * A class represents an animation to scale the shape in width or height, it extends the
// * AbstractAnimation class. It stores the start dimension and end dimension of the animation. It can
// * also call a method to run the animation and get an updated Abstract shape.
// */
//public class ScaleAnimation extends AbstractAnimation {
//  // from Dimension : [Width dimension, Height dimension].
//  private List<Double> fromDimension;
//  // to Dimension : [Width dimension, Height dimension].
//  private List<Double> toDimension;
//
//  /**
//   * Construct an ChangeColorAnimation object.
//   *
//   * @param animationType the type of the animation
//   * @param shapeName     the name of the shape
//   * @param startTime     the start time of the animation
//   * @param endTime       the end time of the animation
//   * @param fromDimension the initial dimension as [Width dimension, Height dimension]
//   * @param toDimension   the final dimension as [Width dimension, Height dimension]
//   * @param originalShape   the originalShape
//   * @throws IllegalArgumentException if the given animation type is invalid
//   */
//  public ScaleAnimation(AnimationType animationType,
//                        String shapeName,
//                        int startTime,
//                        int endTime,
//                        List<Double> fromDimension,
//                        List<Double> toDimension, AbstractShape originalShape)
//      throws IllegalArgumentException {
//    super(animationType, shapeName, startTime, endTime, originalShape);
//    if (animationType != AnimationType.SCALE) {
//      throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
//    }
//    if (fromDimension.size() != 2 || toDimension.size() != 2) {
//      throw new IllegalArgumentException("Trying to initiate with wrong dimension scale.");
//    }
//    this.fromDimension = fromDimension;
//    this.toDimension = toDimension;
//  }
//
//
//  /**
//   * Abstract method to run the animation, which returns an updated abstract shape.
//   *
//   * @param curTime current time
//   * @return an updated abstract shape
//   * @throws IllegalArgumentException if an updated shape cannot be generated
//   */
//  @Override
//  public AbstractShape runAnimation(int curTime)
//      throws IllegalArgumentException {
//    if (originalShape == null) {
//      throw new IllegalArgumentException("shape can not be null");
//    }
//    double curWidth = this.calculateState(fromDimension.get(0), toDimension.get(0), curTime);
//    double curHeight = this.calculateState(fromDimension.get(1), toDimension.get(1), curTime);
//    // Create an updated shape.
//    AbstractShape result = ShapeFactory.buildShape(originalShape.getName(), originalShape.getType(),
//        originalShape.getColor(), originalShape.getPosition(), curWidth, curHeight,
//        originalShape.getAppearTime(), originalShape.getDisappearTime());
//    if (result == null) {
//      throw new IllegalArgumentException("Invalid animation to run on this shape");
//    }
//    return result;
//  }
//
//  /**
//   * Return a String representation of the animation.
//   *
//   * @return a String representation of the animation
//   */
//  @Override
//  public String toString() {
//    return "Shape " + this.getShapeName() + " scales from Width: " + fromDimension.get(0)
//        + " Height: " + fromDimension.get(1)
//        + " to Width: " + toDimension.get(0)
//        + " Height: " + toDimension.get(1)
//        + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n";
//  }
//
//  /**
//   * Return if two animations are equal.
//   *
//   * @param o another object
//   * @return if two animations are equal
//   */
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//
//    if (!(o instanceof ScaleAnimation)) {
//      return false;
//    }
//
//    ScaleAnimation that = (ScaleAnimation) o;
//
//    return this.animationType == that.animationType
//        && this.shapeName.equals(that.shapeName)
//        && this.startTime == that.startTime
//        && this.endTime == that.endTime
//        && this.fromDimension.equals(that.fromDimension)
//        && this.toDimension.equals(that.toDimension);
//  }
//
//  /**
//   * Return the hashcode.
//   *
//   * @return the hashcode
//   */
//  @Override
//  public int hashCode() {
//    return Objects.hash(animationType, shapeName, startTime, endTime,
//        fromDimension, toDimension);
//  }
//}
