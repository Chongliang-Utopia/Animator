package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs5004.animator.model.AnimationOperation;
import cs5004.animator.model.ColorType;
import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Position2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.ShapeType;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test class for TextView.
 */
public class TextViewTest {
  IAnimation rStable1;
  IAnimation rMove2;
  IAnimation rStable3;
  IAnimation rScale4;
  IAnimation rMove5;
  IAnimation rScale6;

  IAnimation cStable1;
  IAnimation cMove2;
  IAnimation cDouble3;
  IAnimation cChangeColor4;
  IAnimation cStable5;
  IAnimation cScale6;

  IReadOnlyShapes rr;
  IReadOnlyShapes cc;

  List<IReadOnlyShapes> shapes;
  Map<Integer, List<IAnimation>> ani;
  Map<Integer, List<IAnimation>> noAni;

  @Before
  public void setUp() {
    rr = new Rectangle("R", ShapeType.RECTANGLE, new ColorType(255, 0, 0),
            new Position2D(200, 200), 50, 100, 1, 100);
    cc = new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
            new Position2D(440, 70), 120, 60, 6, 100);
    // Create Animation.
    rStable1 = new AnimationOperation("R", ShapeType.RECTANGLE,
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0);
    rMove2 = new AnimationOperation("R", ShapeType.RECTANGLE,
            10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0);
    rStable3 = new AnimationOperation("R", ShapeType.RECTANGLE,
            50, 300, 300, 50, 100, 255, 0, 0,
            51, 300, 300, 50, 100, 255, 0, 0);
    rScale4 = new AnimationOperation("R", ShapeType.RECTANGLE,
            51, 300, 300, 50, 100, 255, 0, 0,
            70, 300, 300, 25, 100, 255, 0, 0);
    rMove5 = new AnimationOperation("R", ShapeType.RECTANGLE,
            70, 300, 300, 25, 100, 255, 0, 0,
            100, 200, 200, 25, 100, 255, 0, 0);
    rScale6 = new AnimationOperation("R", ShapeType.RECTANGLE,
            75, 200, 300, 25, 100, 255, 0, 0,
            90, 200, 300, 25, 300, 255, 0, 0);
    cStable1 = new AnimationOperation("C", ShapeType.OVAL,
            6, 440, 70, 120, 60, 0, 0, 255,
            20, 440, 70, 120, 60, 0, 0, 255);
    cMove2 = new AnimationOperation("C", ShapeType.OVAL,
            20, 440, 70, 120, 60, 0, 0, 255,
            50, 440, 250, 120, 60, 0, 0, 255);
    cDouble3 = new AnimationOperation("C", ShapeType.OVAL,
            50, 440, 250, 120, 60, 0, 0, 255,
            70, 440, 370, 120, 60, 0, 170, 85);
    cChangeColor4 = new AnimationOperation("C", ShapeType.OVAL,
            70, 440, 370, 120, 60, 0, 170, 85,
            80, 440, 370, 120, 60, 0, 255, 0);
    cStable5 = new AnimationOperation("C", ShapeType.OVAL,
            80, 440, 370, 120, 60, 0, 255, 0,
            100, 440, 370, 120, 60, 0, 255, 0);
    cScale6 = new AnimationOperation("C", ShapeType.OVAL,
            100, 440, 370, 120, 60, 0, 255, 0,
            120, 440, 370, 60, 60, 0, 255, 0);

    shapes = new ArrayList<>();
    shapes.add(rr);
    shapes.add(cc);
    ani = new TreeMap<>(Comparator.comparingInt(a -> a));
    ani.put(1, List.of(rStable1));
    ani.put(6, List.of(cStable1));
    ani.put(10, List.of(rMove2));
    ani.put(20, List.of(cMove2));
    ani.put(50, List.of(rStable3, cDouble3));
    ani.put(51, List.of(rScale4));
    ani.put(70, List.of(rMove5, cChangeColor4));
    ani.put(75, List.of(rScale6));
    ani.put(80, List.of(cStable5));
    ani.put(100, List.of(cScale6));

    noAni = new TreeMap<>(Comparator.comparingInt(a -> a));
    noAni.put(1, List.of(rStable1));
    noAni.put(6, List.of(cStable1));
    noAni.put(80, List.of(cStable5));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpeed() {
    IView textView = new TextView(-1);
  }

  @Test
  public void testNullInputRenderText() {
    IView textView = new TextView(2);
    assertEquals("", textView.renderText(null, null));
  }

  @Test
  public void testEmptyInputRenderText() {
    IView textView = new TextView(2);
    assertEquals("", textView.renderText(new ArrayList<>(), new HashMap<>()));
  }

  /**
   * Test the text view with the shapes R and C, including 1. animations of move, 2. change color,
   * 3. change width, 4. change height, 5. no changes 6. multiple changes at the same time.
   */
  @Test
  public void testRenderText() {
    IView textView = new TextView(2);
    String expected = "Create rectangle R with color (255,0,0) with corner at (200.0,200.0), "
            + "width: 50 and height 100\n"
            + "Create oval C with color (0,0,255) with center at (440.0,70.0), radius: 120 and 60\n"
            + "\nR appears at time t=0 and disappear at time t=50\n"
            + "C appears at time t=3 and disappear at time t=50\n\n"
            + "R moves from (200.0,200.0) to (300.0,300.0) from time t=5 to t=25\n"
            + "C moves from (440.0,70.0) to (440.0,250.0) from time t=10 to t=25\n"
            + "C changes color from (0,0,255) to (0,170,85) from time t=25 to t=35\n"
            + "C moves from (440.0,250.0) to (440.0,370.0) from time t=25 to t=35\n"
            + "R changes width from 50 to 25 from time t=25 to t=35\n"
            + "R moves from (300.0,300.0) to (200.0,200.0) from time t=35 to t=50\n"
            + "C changes color from (0,170,85) to (0,255,0) from time t=35 to t=40\n"
            + "R changes height from 100 to 300 from time t=37 to t=45\n"
            + "C changes width from 120 to 60 from time t=50 to t=60\n";

    assertEquals(expected, textView.renderText(shapes, ani));
    assertEquals(2, textView.getTempo());
  }

  /**
   * Test the text view with the shapes R and C, but all shapes are stable.
   */
  @Test
  public void testRenderTextNoChanges() {
    IView textView = new TextView(2);
    String expected = "Create rectangle R with color (255,0,0) with corner at (200.0,200.0), "
            + "width: 50 and height 100\n"
            + "Create oval C with color (0,0,255) with center at (440.0,70.0), radius: 120 and 60\n"
            + "\nR appears at time t=0 and disappear at time t=50\n"
            + "C appears at time t=3 and disappear at time t=50\n\n";

    assertEquals(expected, textView.renderText(shapes, noAni));
  }

  @Test(expected = NullPointerException.class)
  public void testNull() {
    IView textView = new TextView(2);
    textView.setCanvas(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testImageRender() {
    IView textView = new TextView(2);
    textView.renderImage(shapes);
  }

}