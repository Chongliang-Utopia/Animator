package cs5004.animator.view;

/**
 * A factory class that enables the user to pick whichever view they like.
 */
public class FactoryView {
  /**
   * a factory method that renders the corresponding view according to the view type and the tempo.
   */
  public static IView makeView(String type) throws IllegalArgumentException {
    switch (type) {
      case "text":
        return new TextView();
      case "visual":
        return new ImageView();
      default:
        throw new IllegalArgumentException("Not a valid view");
    }
  }
}
