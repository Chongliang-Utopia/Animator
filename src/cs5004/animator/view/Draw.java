package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs5004.animator.model.IReadOnlyShapes;

/**
 * A drawing class that will draw all the shapes correspondingly in their positions, colors, and
 * sizes on a canvas with the given size.
 */
public class Draw extends JPanel implements IDraw {
  private Map<Integer, List<IReadOnlyShapes>> animation;

  /**
   * A constructor that is called in the controller to enable this action.
   */
  public Draw() {
    super();
  }

  /**
   * Draw the shapes on the canvas with the corresponding animations.
   *
   * @param allShapes the given shapes with time and list of IReadOnlyShapes
   */
  @Override
  public void draw(Map<String, IReadOnlyShapes> allShapes) {

  }
}
