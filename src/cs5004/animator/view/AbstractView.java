package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

public abstract class AbstractView implements IView {
  protected Screen canvas = new Screen(0, 0, 0, 0);

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   */
  @Override
  public abstract String renderText(Map<String, IReadOnlyShapes> allShapes,
                                  Map<Integer, List<IReadOnlyShapes>> allAnimations);

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   */
  @Override
  public abstract void renderImage(Map<String, IReadOnlyShapes> allShapes);

  /**
   * Set the default canvas to the given size.
   *
   * @param screen the given size canvas
   */
  @Override
  public void setCanvas(Screen screen) {
    this.canvas = screen;
  }

  /**
   * Get the tempo that the user passes in.
   *
   * @return the tempo
   */
  @Override
  public int getTempo() {
    return 0;
  }
}
