package cs5004.animator.controller;

import cs5004.animator.model.IModel;
import cs5004.animator.view.IView;

/**
 * A factory class that enables the user to pick whichever controller they like.
 */
public class FactoryController {
  /**
   * A factory method that renders the corresponding controller
   * according to the view type .
   *
   * @param type the type of the controller want to generate
   * @return the IView object
   * @throws IllegalArgumentException if the input is invalid controller
   */
  public static IController makeController(String type, IModel model, IView view)
      throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Not a valid controller");
    }
    switch (type) {
      case "text":
        return new TextViewControllerImpl(model, view);
      case "visual":
        return new ImageViewControllerImpl(model, view);
      default:
        throw new IllegalArgumentException("Not a valid controller");
    }
  }
}
