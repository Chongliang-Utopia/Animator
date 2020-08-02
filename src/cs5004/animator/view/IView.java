package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

public interface IView {

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   */
  void renderText(Map<String, IReadOnlyShapes> allShapes,
                  Map<Integer, List<IReadOnlyShapes>> allAnimations);

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   */
  void renderImage(Map<String, IReadOnlyShapes> allShapes);

  /**
   * Set the default canvas to the given size.
   *
   * @param screen the given size canvas
   */
  void setCanvas(Screen screen);

}
