package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.IReadOnlyShapes;

/**
 * The IDraw interface will draw all the shapes correspondingly in their positions, colors, and
 * sizes on a canvas.
 */
public interface IDraw {

  /**
   * Draw the shapes on the canvas with the corresponding animations.
   *
   * @param animation the given animation with time and list of IReadOnlyShapes
   */
  void draw(Map<Integer, List<IReadOnlyShapes>> animation);
}

