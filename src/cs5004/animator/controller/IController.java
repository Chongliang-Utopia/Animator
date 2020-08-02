package cs5004.animator.controller;

import java.io.IOException;

/**
 * A controller interface that enables the program to output the description of the shapes, or draws
 * the animation on a canvas.
 */
public interface IController {
  /**
   * The method that will render the text, or draw the shapes.
   */
  void run(Appendable ap) throws  IOException;

  /**
   * Changes the speed of the animations to the given speed.
   *
   * @param value the given speed
   */
  void changeSpeed(int value);
}
