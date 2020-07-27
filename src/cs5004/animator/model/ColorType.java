package cs5004.animator.model;

import java.util.Objects;

public class ColorType {
  private float red;
  private float green;
  private float blue;

  /**
   * Construct Colors object based on decimal numbers of red, green, and blue.
   *  @param red   the red value of the color
   * @param green the green value of the color
   * @param blue  the blue value of the color
   */
  public ColorType(float red, float green, float blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Get the blue value of the color.
   *
   * @return the float value of the blue part of the color
   */
  public float getBlue() {
    return blue;
  }

  /**
   * Get the green value of the color.
   *
   * @return the float value of the green part of the color
   */
  public float getGreen() {
    return green;
  }

  /**
   * Get the red value of the color.
   *
   * @return the float value of the red part of the color
   */
  public float getRed() {
    return red;
  }

  /**
   * String representation of the color settings, (red, green, blue) e.g. (0.0,0.0,1.0)
   *
   * @return the string format of the color settings
   */
  @Override
  public String toString() {
    return "(" + String.format("%.1f", red) + ","
            + String.format("%.1f", green) + "," + String.format("%.1f", blue) + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ColorType)) {
      return false;
    }
    ColorType other = (ColorType) o;
    return this.red == other.red && this.green == other.green && this.blue == other.blue;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.red, this.blue, this.green);
  }

}
