package cs5004.animator.view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;

import javax.swing.JPanel;

import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.ShapeType;

/**
 * A drawing class that will draw all the shapes correspondingly in their positions, colors, and
 * sizes on a canvas with the given size.
 */
public class Draw extends JPanel implements IDraw {
  private List<IReadOnlyShapes> shapes;

  /**
   * A constructor that is called in the controller to enable this action.
   */
  public Draw() {
    super();
  }

  /**
   * Draws a shape with corresponding position, color, and size.
   *
   * @param g given graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (this.shapes != null) {
      Graphics2D g2d = (Graphics2D) g;
      g.setColor(Color.BLACK);

      for (IReadOnlyShapes shape : this.shapes) {
        g2d.setColor(new Color(shape.getColor().getRed(),
                shape.getColor().getGreen(), shape.getColor().getBlue()));
        if (shape.getType() == ShapeType.OVAL) {
          g2d.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        } else if (shape.getType() == ShapeType.RECTANGLE) {
          g2d.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                  (int) shape.getWidth(), (int) shape.getHeight());
        }
      }

    }
  }

  /**
   * Draw the shapes on the canvas with the corresponding animations.
   *
   * @param shapes the given shapes with time and list of IReadOnlyShapes
   */
  @Override
  public void draw(List<IReadOnlyShapes> shapes) {
    this.shapes = shapes;
    repaint();
  }
}
