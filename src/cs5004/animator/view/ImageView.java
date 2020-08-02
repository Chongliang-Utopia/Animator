package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JFrame;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

public class ImageView extends AbstractView {
  private final Draw panel;

  /**
   * Construct an ImageView.
   */
  public ImageView() {
    super();
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
    panel.draw(new ArrayList<>(allShapes.values()));
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
