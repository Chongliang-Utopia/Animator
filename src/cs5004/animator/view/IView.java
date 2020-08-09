package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

/**
 * An IView interface that enables two different views including text view and canvas view.
 */
public interface IView {

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   * @throws UnsupportedOperationException if renderText has been called in the view type that does
   *                                       not support text output
   */
  String renderText(List<IReadOnlyShapes> allShapes,
                    Map<Integer, List<IAnimation>> allAnimations)
      throws UnsupportedOperationException;

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   * @throws UnsupportedOperationException if renderImage has been called in the view type that does
   *                                       not support image output
   */
  void renderImage(List<IReadOnlyShapes> allShapes) throws UnsupportedOperationException;


  /**
   * Set the default canvas to the given size.
   *
   * @param screen the given size canvas
   * @throws NullPointerException if the given screen is null
   */
  void setCanvas(Screen screen) throws NullPointerException;

  /**
   * Get the tempo that the user passes in.
   *
   * @return the tempo
   */
  int getTempo();

  /**
   * Adds all the IViewFeatures to the listeners to execute.
   *
   * @param vf given IViewFeatures
   * @throws UnsupportedOperationException if the given view doesn't support the listeners
   */
  void addListener(IViewFeatures vf) throws UnsupportedOperationException;

  /**
   * Checks if the given view has a listener field.
   *
   * @return true if it has a listener field
   */
  boolean hasListener();

}
