package cs5004.animator.model;

/**
 * Type of various kinds of 2D shapes in animation.
 */
public enum ShapeType {
  RECTANGLE("rectangle"), OVAL("oval");
  private String shape;
  ShapeType(String shape) {
    this.shape = shape;
  }

  @Override
  public String toString() {
    return this.shape;
  }
}
