package cs5004.animator.model;

import java.util.Objects;

/**
 * This class represents a color using three float values, which are in the order of Red, Green, and
 * Blue. It contains the methods to get the attributes properly, as well as the overridden toString,
 * equals, and hashCode.
 */
public class ColorType {
  private int red;
  private int green;
  private int blue;

  /**
   * Construct Colors object based on numbers of red, green, and blue.
   *
   * @param red   the red value of the color
   * @param green the green value of the color
   * @param blue  the blue value of the color
   * @throws IllegalArgumentException if the color RGB parameters are not within 0-255
   */
  public ColorType(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("Invalid RGB value for color");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Get the blue value of the color.
   *
   * @return the float value of the blue part of the color
   */
  public int getBlue() {
    return blue;
  }

  /**
   * Get the green value of the color.
   *
   * @return the float value of the green part of the color
   */
  public int getGreen() {
    return green;
  }

  /**
   * Get the red value of the color.
   *
   * @return the float value of the red part of the color
   */
  public int getRed() {
    return red;
  }

  /**
   * String representation of the color settings, (red, green, blue) e.g. (255, 255, 255)
   *
   * @return the string format of the color settings
   */
  @Override
  public String toString() {
    return "(" + String.format("%d", red) + ","
            + String.format("%d", green) + "," + String.format("%d", blue) + ")";
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
