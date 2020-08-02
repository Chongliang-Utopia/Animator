package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IReadOnlyShapes;

public class ImageView extends AbstractView {
  private int tempo;

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   */
  @Override
  public String renderText(Map<String, IReadOnlyShapes> allShapes,
                           Map<Integer, List<AbstractAnimation>> allAnimations) {
    return null;
  }

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   */
  @Override
  public void renderImage(Map<String, IReadOnlyShapes> allShapes) {

  }
}
