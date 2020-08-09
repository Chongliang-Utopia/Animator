package cs5004.animator.view;

import java.util.List;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

/**
 * ImageView class extends AbstractView. It renders the animation of the motions of all the shapes
 * on the canvas according to the given list of shapes.
 */
public class ImageView extends AbstractView {
  private final Draw panel;

  /**
   * Construct an image view with the given tempo.
   *
   * @param tempo the speed of the view
   * @throws IllegalArgumentException if the speed is invalid
   */
  public ImageView(int tempo) throws IllegalArgumentException {
    super(tempo);
    panel = new Draw();
    panel.setBackground(Color.WHITE);

    JScrollPane scrollPane = new JScrollPane(panel);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    add(scrollPane);
    setVisible(true);

  }

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   * @throws UnsupportedOperationException if renderText has been called
   */
  @Override
  public String renderText(List<IReadOnlyShapes> allShapes,
                           Map<Integer, List<IAnimation>> allAnimations)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Image view cannot provide text output.");
  }

  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   */
  @Override
  public void renderImage(List<IReadOnlyShapes> allShapes) {
    panel.draw(allShapes);
  }

  /**
   * Set the default canvas to the given size.
   *
   * @param c given canvas size
   */
  @Override
  public void setCanvas(Screen c) {
    this.canvas = c;
    panel.setBounds(canvas.getLocX(), canvas.getLocY(), canvas.getCanvasW(), canvas.getCanvasH());
    setSize(1000, 800);
    panel.setPreferredSize(new Dimension(canvas.getCanvasW(), canvas.getCanvasH()));
  }


  /**
   * Checks if the given view has a listener field.
   *
   * @return true if it has a listener field
   */
  public boolean hasListener() {
    return false;
  }

  /**
   * Adds all the IViewFeatures to the listeners to execute.
   *
   * @param vf given IViewFeatures
   * @throws UnsupportedOperationException if the given view doesn't support the listeners
   */
  @Override
  public void addListener(IViewFeatures vf) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("yeehaw");
  }
}
