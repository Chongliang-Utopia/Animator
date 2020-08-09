package cs5004.animator.view;

/**
 * A factory class that enables the user to pick whichever view they like.
 */
public class FactoryView {
  /**
   * A factory method that renders the corresponding view according to the view type and the tempo.
   *
   * @param type the type of the view want to generate
   * @return the IView object
   * @throws IllegalArgumentException if the input is invalid view
   */
  public static IView makeView(String type, int tempo) throws IllegalArgumentException {
    if (type == null) {
      throw new IllegalArgumentException("Not a valid view");
    }
    switch (type) {
      case "text":
        return new TextView(tempo);
      case "visual":
        return new ImageView(tempo);
      case "edit":
        return new EditorView(new ImageView(tempo));
      default:
        throw new IllegalArgumentException("Not a valid view");
    }
  }
}
