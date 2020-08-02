package cs5004.animator.view;

import java.util.List;
import java.util.Map;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IReadOnlyShapes;

public class TextView extends AbstractView {
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
    StringBuilder text = new StringBuilder();
    // add canvas info to the view
    text.append(String.format("Canvas %s\n\n", this.canvas.toString()));
    text.append("Create the following shapes: \n");
    // add shape info to the view
    for (String name: allShapes.keySet()) {
      IReadOnlyShapes shape = allShapes.get(name);
      text.append(shape.textRender());
    }
    text.append("\n");

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
   * @param allShapes the given animations in the view
   */
  public void renderImage(Map<String, IReadOnlyShapes> allShapes) {

  }
}
