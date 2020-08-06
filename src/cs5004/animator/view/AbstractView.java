package cs5004.animator.view;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

/**
 * An Abstract class for various types of views, that extends extends JFrame implements IView. It
 * includes method renderText for the text view and method renderImage for the image view. It can
 * also get the tempo and set the canvas.
 */
public abstract class AbstractView extends JFrame implements IView {
  protected int tempo;
  protected Screen canvas = new Screen(0, 0, 0, 0);

  /**
   * Construct an AbstractView object with the given tempo, tick per second.
   *
   * @param tempo the speed of the view
   * @throws IllegalArgumentException if the speed is invalid
   */
  public AbstractView(int tempo) throws IllegalArgumentException {
    if (tempo <= 0) {
      throw new IllegalArgumentException("Invalid speed.");
    }
    this.tempo = tempo;
  }

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   * @throws UnsupportedOperationException if renderText has been called in the view type that does
   *                                       not support text output
   */
  @Override
  public abstract String renderText(List<IReadOnlyShapes> allShapes,
                                    Map<Integer, List<IAnimation>> allAnimations);

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   * @throws UnsupportedOperationException if renderImage has been called in the view type that does
   *                                       not support image output
   */
  @Override
  public abstract void renderImage(List<IReadOnlyShapes> allShapes);

  /**
   * Set the default canvas to the given size.
   *
   * @param screen the given size canvas
   * @throws NullPointerException if the given screen is null
   */
  @Override
  public void setCanvas(Screen screen) throws NullPointerException {
    Objects.requireNonNull(screen);
    this.canvas = screen;
  }

  /**
   * Get the tempo that the user passes in.
   *
   * @return the tempo
   */
  @Override
  public int getTempo() {
    return this.tempo;
  }
}
