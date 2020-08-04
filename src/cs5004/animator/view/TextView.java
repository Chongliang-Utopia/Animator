package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IReadOnlyShapes;

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
   *
   * @param allShapes     the given shapes in the view
   * @param allAnimations the given animations in the view
   * @return the string output of the text view
   */
  @Override
  public String renderText(List<IReadOnlyShapes> allShapes,
                           Map<Integer, List<AbstractAnimation>> allAnimations) {
    StringBuilder text = new StringBuilder();
    // add canvas info to the view
    // text.append(String.format("Canvas %s\n\n", this.canvas.toString()));

    // add create shape info to the view
    for (IReadOnlyShapes shape : allShapes) {
      text.append("Create ")
              .append(shape.getType().toString()).append(" ")
              .append(shape.getName())
              .append(" with color ").append(shape.getColor().toString()).append(" ")
              .append(shape.stringPosSize());
    }
    text.append("\n");

    // add shape display info to the view
    for (IReadOnlyShapes shape : allShapes) {
      text.append(shape.getName())
              .append(" appears at time t=").append(shape.getAppearTime()/this.tempo)
              .append(" and disappear at time t=").append(shape.getDisappearTime()/this.tempo)
              .append("\n");
    }
    text.append("\n");

    // add shape animation info to the view
    for (List<AbstractAnimation> aniLst : allAnimations.values()) {
      for (AbstractAnimation ani : aniLst) {
        text.append(ani.toString());
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
