package cs5004.animator.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a JUnit Test for the AbstractShape class, including its concrete classes Rectangle and
 * Oval.
 */
public class ShapeTest {

  @Test
  public void rectangleShapeGetter() {
    AbstractShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
    assertEquals("R", rectangle.getName());
    assertEquals("rectangle", rectangle.getType().toString());
    assertEquals(new Position2D(200.0, 200.0), rectangle.getPosition());
    assertEquals(new ColorType(255, 139, 234), rectangle.getColor());
    assertEquals(50.0, rectangle.getWidth(), 0.0);
    assertEquals(100.0, rectangle.getHeight(), 0.0);
    assertEquals(1, rectangle.getAppearTime(), 0.0);
    assertEquals(100, rectangle.getDisappearTime(), 0.0);
    assertTrue(rectangle.getDisplay());
  }

  @Test
  public void rectangleShapeSetter() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);

    rectangle.setDisplay(false);
    assertFalse(rectangle.getDisplay());
    rectangle.setDisplay(true);
    assertTrue(rectangle.getDisplay());

    ColorType newColor = new ColorType(255, 139, 234);
    rectangle.setColor(newColor);
    assertEquals(new ColorType(255, 139, 234), rectangle.getColor());

    assertEquals(new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100), rectangle);
  }

  @Test
  public void rectangleShapeText() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
    assertEquals("Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, " +
                    "Height: 100.0, Color: (255,139,234)\nAppears at t=1\nDisappears at t=100\n",
            rectangle.textRender());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullName() {
    IShape rectangle = new Rectangle(null, ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullType() {
    IShape rectangle = new Rectangle("R", null,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullColor() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            null, new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullPosition() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), null,
            50.0, 100.0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidAppear() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, -1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidDisappear() {
    IShape rectangle = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 50, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidWidthHeight() {
    IShape rectangle1 = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            0, 2, 1, 22);

    IShape rectangle2 = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            2, 0, 1, 22);
  }

  @Test
  public void ovalShapeGetter() {
    IShape oval = new Oval("O", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
    assertEquals("O", oval.getName());
    assertEquals("oval", oval.getType().toString());
    assertEquals(new Position2D(500.0, 100.0), oval.getPosition());
    assertEquals(new ColorType(255, 139, 234), oval.getColor());
    assertEquals(60.0, oval.getWidth(), 0.0);
    assertEquals(30.0, oval.getHeight(), 0.0);
    assertEquals(6, oval.getAppearTime(), 0.0);
    assertEquals(100, oval.getDisappearTime(), 0.0);
    assertTrue(oval.getDisplay());
  }

  @Test
  public void ovalShapeSetter() {
    IShape oval = new Oval("O", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);

    oval.setDisplay(false);
    assertFalse(oval.getDisplay());
    oval.setDisplay(true);
    assertTrue(oval.getDisplay());

    ColorType newColor = new ColorType(222, 139, 234);
    oval.setColor(newColor);
    assertEquals(new ColorType(222, 139, 234), oval.getColor());

    assertEquals(new Oval("O", ShapeType.OVAL,
            new ColorType(222, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100), oval);
  }

  @Test
  public void ovalShapeText() {
    IShape oval = new Oval("O", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
    assertEquals("Name: O\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
                    + "Y radius: 30.0, Color: (255,139,234)\nAppears at t=6\nDisappears at t=100\n",
            oval.textRender());
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullOvalName() {
    IShape oval = new Oval(null, ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullOvalType() {
    IShape oval = new Oval("R", null,
            new ColorType(255, 139, 234), new Position2D(500.0, 100.0),
            60.0, 30.0, 6, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void nullOvalColor() {
    IShape oval = new Oval("R", ShapeType.OVAL,
            null, new Position2D(200.0, 200.0),
            50.0, 100.0, 1, 100);
  }



  @Test(expected = IllegalArgumentException.class)
  public void nullOvalPosition() {
    IShape oval = new Oval("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), null,
            50.0, 100.0, 1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalAppear() {
    IShape oval = new Oval("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, -1, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalDisappear() {
    IShape oval = new Oval("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            50.0, 100.0, 50, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidOvalWidthHeight() {
    IShape oval1 = new Oval("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            0, 2, 1, 22);

    IShape oval2 = new Oval("R", ShapeType.OVAL,
            new ColorType(255, 139, 234), new Position2D(200.0, 200.0),
            2, 0, 1, 22);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidColor() {
    ColorType color = new ColorType(-1, 255, 255);
  }
}