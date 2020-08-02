//<<<<<<< HEAD
////package cs5004.animator.model;
////
////import java.util.Objects;
////
/////**
//// * A class represents an animation to change the color, it extends the AbstractAnimation class. It
//// * stores the start color and end color of the animation. It can also call a method to run the
//// * animation and get an updated Abstract shape.
//// */
////public class ChangeColorAnimation extends AbstractAnimation {
////  ColorType startColor;
////  ColorType endColor;
////
////  /**
////   * Construct an ChangeColorAnimation object.
////   *
////   * @param animationType the type of the animation
////   * @param shapeName     the name of the shape
////   * @param startTime     the start time of the animation
////   * @param endTime       the end time of the animation
////   * @param startColor    the start color of the animation
////   * @param endColor      the end color of the animation
////   * @param originalShape   the originalShape
////   * @throws IllegalArgumentException if the given animation type is invalid
////   */
////  public ChangeColorAnimation(AnimationType animationType,
////                              String shapeName,
////                              int startTime,
////                              int endTime,
////                              ColorType startColor,
////                              ColorType endColor, AbstractShape originalShape)
////      throws IllegalArgumentException {
////    super(animationType, shapeName, startTime, endTime, originalShape);
////    if (animationType != AnimationType.CHANGECOLOR) {
////      throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
////    }
////    this.startColor = startColor;
////    this.endColor = endColor;
////  }
////
////
////  /**
////   * Abstract method to run the animation, which returns an updated abstract shape.
////   *
////   * @param curTime current time
////   * @return an updated abstract shape
////   * @throws IllegalArgumentException if an updated shape cannot be generated
////   */
////  @Override
////  public AbstractShape runAnimation(int curTime)
////      throws IllegalArgumentException {
////    if (originalShape == null) {
////      throw new IllegalArgumentException("shape can not be null");
////    }
////    ColorType curColor = new ColorType(
////        (float) this.calculateState(startColor.getRed(), endColor.getRed(), curTime),
////        (float) this.calculateState(startColor.getGreen(), endColor.getGreen(), curTime),
////        (float) this.calculateState(startColor.getBlue(), endColor.getBlue(), curTime));
////    // Create an updated shape.
////    AbstractShape result = ShapeFactory.buildShape(originalShape.getName(), originalShape.getType(), curColor,
////        originalShape.getPosition(), originalShape.getWidth(), originalShape.getHeight(),
////        originalShape.getAppearTime(), originalShape.getDisappearTime());
////    if (result == null) {
////      throw new IllegalArgumentException("Invalid animation to run on this shape");
////    }
////    return result;
////  }
////
////  /**
////   * Return a String representation of the animation.
////   *
////   * @return a String representation of the animation
////   */
////  @Override
////  public String toString() {
////    return "Shape " + this.getShapeName() + " changes color from " + startColor.toString()
////        + " to " + endColor.toString()
////        + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n";
////  }
////
////
////  /**
////   * Return if two animations are equal.
////   *
////   * @param o another object
////   * @return if two animations are equal
////   */
////  @Override
////  public boolean equals(Object o) {
////    if (this == o) {
////      return true;
////    }
////
////    if (!(o instanceof ChangeColorAnimation)) {
////      return false;
////    }
////
////    ChangeColorAnimation that = (ChangeColorAnimation) o;
////
////    return this.animationType == that.animationType
////        && this.shapeName.equals(that.shapeName)
////        && this.startTime == that.startTime
////        && this.endTime == that.endTime
////        && this.startColor.equals(that.startColor)
////        && this.endColor.equals(that.endColor);
////  }
////
////  /**
////   * Return the hashcode.
////   *
////   * @return the hashcode
////   */
////  @Override
////  public int hashCode() {
////    return Objects.hash(animationType, shapeName, startTime, endTime,
////        startColor, endColor);
////  }
////}
//=======
//package cs5004.animator.model;
//
//import java.util.Objects;
//
///**
// * A class represents an animation to change the color, it extends the AbstractAnimation class. It
// * stores the start color and end color of the animation. It can also call a method to run the
// * animation and get an updated Abstract shape.
// */
//public class ChangeColorAnimation extends AbstractAnimation {
//  ColorType startColor;
//  ColorType endColor;
//
//  /**
//   * Construct an ChangeColorAnimation object.
//   *
//   * @param animationType the type of the animation
//   * @param shapeName     the name of the shape
//   * @param startTime     the start time of the animation
//   * @param endTime       the end time of the animation
//   * @param startColor    the start color of the animation
//   * @param endColor      the end color of the animation
//   * @param originalShape the originalShape
//   * @throws IllegalArgumentException if the given animation type is invalid
//   */
//  public ChangeColorAnimation(AnimationType animationType,
//                              String shapeName,
//                              int startTime,
//                              int endTime,
//                              ColorType startColor,
//                              ColorType endColor, AbstractShape originalShape) throws IllegalArgumentException {
//    super(animationType, shapeName, startTime, endTime, originalShape);
//    if (animationType != AnimationType.CHANGECOLOR) {
//      throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
//    }
//    this.startColor = startColor;
//    this.endColor = endColor;
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
//          throws IllegalArgumentException {
//    if (originalShape == null) {
//      throw new IllegalArgumentException("shape can not be null");
//    }
//    ColorType curColor = new ColorType(
//            (int) this.calculateState(startColor.getRed(), endColor.getRed(), curTime),
//            (int) this.calculateState(startColor.getGreen(), endColor.getGreen(), curTime),
//            (int) this.calculateState(startColor.getBlue(), endColor.getBlue(), curTime));
//    // Create an updated shape.
//    AbstractShape result = ShapeFactory.buildShape(originalShape.getName(), originalShape.getType(), curColor,
//            originalShape.getPosition(), originalShape.getWidth(), originalShape.getHeight(),
//            originalShape.getAppearTime(), originalShape.getDisappearTime());
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
//    return "Shape " + this.getShapeName() + " changes color from " + startColor.toString()
//            + " to " + endColor.toString()
//            + " from t=" + this.getStartTime() + " to t=" + this.getEndTime() + "\n";
//  }
//
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
//    if (!(o instanceof ChangeColorAnimation)) {
//      return false;
//    }
//
//    ChangeColorAnimation that = (ChangeColorAnimation) o;
//
//    return this.animationType == that.animationType
//            && this.shapeName.equals(that.shapeName)
//            && this.startTime == that.startTime
//            && this.endTime == that.endTime
//            && this.startColor.equals(that.startColor)
//            && this.endColor.equals(that.endColor);
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
//            startColor, endColor);
//  }
//}
//>>>>>>> 0319694603ae2903aca90a4ba7685033ffe81539
