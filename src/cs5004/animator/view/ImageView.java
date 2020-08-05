package cs5004.animator.view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;


import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

/**
 * A class that renders the animation of the motions of all the shapes on the canvas.
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
   */
  @Override
  public String renderText(List<IReadOnlyShapes> allShapes,
                           Map<Integer, List<AbstractAnimation>> allAnimations) {
    return null;
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
}
