package cs5004.animator.model;

import java.util.List;

/**
 * This interface represents a motion to transform the original shape, it declares methods that
 * a motion needs, to run the motion and return a shape with updated states, and return information
 * about the motion.
 */

public interface IAnimation {
  /**
   * Return the name of the shape in the animation.
   *
   * @return the name of the shape in the animation
   */
  String getShapeName();

  /**
   * Return the start time of the animation.
   *
   * @return the start time of the animation
   */
  int getStartTime();

  /**
   * Return the end time of the animation.
   *
   * @return the end time of the animation
   */
  int getEndTime();

  /**
   * Return a list of start position and end position of the animation.
   *
   * @return a list of start position and end position of the animation
   */
  List<Position2D> getPos();

  /**
   * Return a list of start color and end color of the animation.
   *
   * @return a list of start color and end color of the animation
   */
  List<ColorType> getColor();

  /**
   * Return a list of start dimension and end dimension of the animation.
   *
   * @return a list of start dimension and end dimension of the animation
   */
  List<int[]> getDimension();


  /**
   * Run the animation, which returns an updated abstract shape.
   *
   * @param curTime current time
   * @return an updated abstract shape
   * @throws IllegalArgumentException if an updated shape cannot be generated
   */
  AbstractShape runAnimation(int curTime) throws
      IllegalArgumentException;
}
