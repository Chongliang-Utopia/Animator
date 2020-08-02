package cs5004.animator.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit Test for the ShapeFactory class, including its concrete classes Rectangle and
 * Oval.
 */
public class ShapeFactoryTest {

  @Test
  public void buildShape() {
    AbstractShape rec = ShapeFactory.buildShape("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, " +
                    "Height: 100.0, Color: (255,139,234)\nAppears at t=1\nDisappears at t=100\n",
            rec.textRender());

    AbstractShape oval = ShapeFactory.buildShape("O", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
    assertEquals("O", oval.getName());
    assertEquals("Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (255,139,234)\nAppears at t=6\nDisappears at t=100\n",
            oval.textRender());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullType() {
    AbstractShape oval = ShapeFactory.buildShape("R", null,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAppearDisappear() {
    AbstractShape oval = ShapeFactory.buildShape("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 50, 2);
  }



}