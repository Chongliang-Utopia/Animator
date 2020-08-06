package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;

/**
 * TextView class extends AbstractView. It renders the text of the motions of all the shapes. It has
 * the implementation of the method renderText, which takes in shapes and animations and creates a
 * text output with three parts of the information: 1. shape creation information including all
 * shape's details 2. shape appear and disappear time (in second) 3. animations on the shape
 */
public class TextView extends AbstractView {
  /**
   * Construct an AbstractView object with the given tempo, tick per second.
   *
   * @param tempo the speed of the view
   * @throws IllegalArgumentException if the speed is invalid
   */
  public TextView(int tempo) throws IllegalArgumentException {
    super(tempo);
  }

  /**
   * Render the text view output, including the text information of all shapes and all animations.
   * Return empty string is the given shapes and animations are empty or null.
   * An example is as follows:
   * <p>Create rectangle R with color (255,0,0) with corner at (200.0,200.0), width: 50 and height
   * 100
   * Create oval C with color (0,0,255) with center at (440.0,70.0), radius: 120 and 60
   * <p>R appears at time t=0 and disappear at time t=50 C appears at time t=3 and disappear at time
   * t=50
   * <p>R moves from (200.0,200.0) to (300.0,300.0) from time t=5 to t=25
   * C moves from (440.0,70.0) to (440.0,250.0) from time t=10 to t=25
   * C changes color from (0,0,255) to (0,170,85) from time t=25 to t=35
   * R changes width from 50 to 25 from time t=25 to t=35
   * R moves from (300.0,300.0) to (200.0,200.0) from time t=35to t=50
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   */
  @Override
  public String renderText(List<IReadOnlyShapes> allShapes,
                           Map<Integer, List<IAnimation>> allAnimations) {
    if (allShapes == null || allShapes.size() == 0 || allAnimations == null
            || allAnimations.size() == 0) {
      return "";
    }
    StringBuilder text = new StringBuilder();

    // add create shape info to the view
    for (IReadOnlyShapes shape : allShapes) {
      text.append("Create ")
              .append(shape.getType().toString()).append(" ")
              .append(shape.getName())
              .append(" with color ").append(shape.getColor().toString()).append(" ");
      switch (shape.getType()) {
        case RECTANGLE:
          text.append("with corner at ").append(shape.getPosition().toString())
                  .append(String.format(", width: %.0f and height %.0f\n", shape.getWidth(),
                          shape.getHeight()));
          break;
        case OVAL:
          text.append("with center at ").append(shape.getPosition().toString())
                  .append(String.format(", radius: %.0f and %.0f\n", shape.getWidth(),
                          shape.getHeight()));
          break;
        default:
      }
    }
    text.append("\n");

    // add shape display info to the view
    for (IReadOnlyShapes shape : allShapes) {
      text.append(shape.getName())
              .append(" appears at time t=").append(shape.getAppearTime() / this.tempo)
              .append(" and disappear at time t=").append(shape.getDisappearTime() / this.tempo)
              .append("\n");
    }
    text.append("\n");

    // add shape animation info to the view
    for (List<IAnimation> aniLst : allAnimations.values()) {
      for (IAnimation ani : aniLst) {

        if (!ani.getColor().get(0).equals(ani.getColor().get(1))) {
          text.append(ani.getShapeName());
          text.append(" changes color from ").append(ani.getColor().get(0).toString())
                  .append(" to ").append(ani.getColor().get(1).toString())
                  .append(" from time t=").append(ani.getStartTime() / this.tempo)
                  .append(" to t=").append(ani.getEndTime() / this.tempo).append("\n");
        }
        if (!ani.getPos().get(0).equals(ani.getPos().get(1))) {
          text.append(ani.getShapeName());
          text.append(" moves from ").append(ani.getPos().get(0).toString())
                  .append(" to ").append(ani.getPos().get(1).toString())
                  .append(" from time t=").append(ani.getStartTime() / this.tempo)
                  .append(" to t=").append(ani.getEndTime() / this.tempo).append("\n");
        }
        if (ani.getDimension().get(0)[0] != ani.getDimension().get(1)[0]) {
          text.append(ani.getShapeName());
          text.append(" changes width from ").append(ani.getDimension().get(0)[0])
                  .append(" to ").append(ani.getDimension().get(1)[0])
                  .append(" from time t=").append(ani.getStartTime() / this.tempo)
                  .append(" to t=").append(ani.getEndTime() / this.tempo).append("\n");
        }
        if (ani.getDimension().get(0)[1] != ani.getDimension().get(1)[1]) {
          text.append(ani.getShapeName());
          text.append(" changes height from ").append(ani.getDimension().get(0)[1])
                  .append(" to ").append(ani.getDimension().get(1)[1])
                  .append(" from time t=").append(ani.getStartTime() / this.tempo)
                  .append(" to t=").append(ani.getEndTime() / this.tempo).append("\n");
        }
      }
    }
    return text.toString();
  }


  /**
   * Render the image view output with all needed information for drawing shapes.
   *
   * @param allShapes the given shapes in the view
   */
  @Override
  public void renderImage(List<IReadOnlyShapes> allShapes) {

  }
}
