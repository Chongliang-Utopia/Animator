//package cs5004.animator.model;
//
//import java.util.Objects;
//
///**
// * Represent an animation where the shape does not change during the animation.
// */
//public class StableAnimation extends AbstractAnimation {
//
//  public StableAnimation(AnimationType animationType,
//                              String shapeName,
//                              int startTime,
//                              int endTime, AbstractShape originalShape)
//      throws IllegalArgumentException {
//    super(animationType, shapeName, startTime, endTime, originalShape);
//    if (animationType != AnimationType.STABLE) {
//      throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
//    }
//  }
//
//  @Override
//  public AbstractShape runAnimation(int curTime) throws IllegalArgumentException {
//    AbstractShape result = ShapeFactory.buildShape(originalShape.getName(), originalShape.getType(),
//        originalShape.getColor(), originalShape.getPosition(), originalShape.getWidth(),
//        originalShape.getHeight(), originalShape.getAppearTime(), originalShape.getDisappearTime());
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
//    return "";
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
//    if (!(o instanceof StableAnimation)) {
//      return false;
//    }
//
//    StableAnimation that = (StableAnimation) o;
//
//    return this.animationType == that.animationType
//        && this.shapeName.equals(that.shapeName)
//        && this.startTime == that.startTime
//        && this.endTime == that.endTime;
//  }
//
//  /**
//   * Return the hashcode.
//   *
//   * @return the hashcode
//   */
//  @Override
//  public int hashCode() {
//    return Objects.hash(animationType, shapeName, startTime, endTime);
//  }
//}
