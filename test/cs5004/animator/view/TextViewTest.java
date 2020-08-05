package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.AnimationOperation;
import cs5004.animator.model.ColorType;
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
  AbstractAnimation R1_Stable;
  AbstractAnimation R2_Move;
  AbstractAnimation R3_Stable;
  AbstractAnimation R4_Scale;
  AbstractAnimation R5_Move;
  AbstractAnimation R6_Scale;

  AbstractAnimation C1_Stable;
  AbstractAnimation C2_Move;
  AbstractAnimation C3_Double;
  AbstractAnimation C4_ChangeColor;
  AbstractAnimation C5_Stable;
  AbstractAnimation C6_Scale;

  IReadOnlyShapes rr;
  IReadOnlyShapes cc;

  List<IReadOnlyShapes> shapes;
  Map<Integer, List<AbstractAnimation>> ani;
  Map<Integer, List<AbstractAnimation>> noAni;

  @Before
  public void setUp() {
    rr = new Rectangle("R", ShapeType.RECTANGLE, new ColorType(255, 0, 0),
            new Position2D(200, 200), 50, 100, 1, 100);
    cc = new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
            new Position2D(440, 70), 120, 60, 6, 100);
    // Create Animation.
    R1_Stable = new AnimationOperation("R", ShapeType.RECTANGLE,
            1, 200, 200, 50, 100, 255, 0, 0,
            10, 200, 200, 50, 100, 255, 0, 0);
    R2_Move = new AnimationOperation("R", ShapeType.RECTANGLE,
            10, 200, 200, 50, 100, 255, 0, 0,
            50, 300, 300, 50, 100, 255, 0, 0);
    R3_Stable = new AnimationOperation("R", ShapeType.RECTANGLE,
            50, 300, 300, 50, 100, 255, 0, 0,
            51, 300, 300, 50, 100, 255, 0, 0);
    R4_Scale = new AnimationOperation("R", ShapeType.RECTANGLE,
            51, 300, 300, 50, 100, 255, 0, 0,
            70, 300, 300, 25, 100, 255, 0, 0);
    R5_Move = new AnimationOperation("R", ShapeType.RECTANGLE,
            70, 300, 300, 25, 100, 255, 0, 0,
            100, 200, 200, 25, 100, 255, 0, 0);
    R6_Scale = new AnimationOperation("R", ShapeType.RECTANGLE,
            75, 200, 300, 25, 100, 255, 0, 0,
            90, 200, 300, 25, 300, 255, 0, 0);
    C1_Stable = new AnimationOperation("C", ShapeType.OVAL,
            6, 440, 70, 120, 60, 0, 0, 255,
            20, 440, 70, 120, 60, 0, 0, 255);
    C2_Move = new AnimationOperation("C", ShapeType.OVAL,
            20, 440, 70, 120, 60, 0, 0, 255,
            50, 440, 250, 120, 60, 0, 0, 255);
    C3_Double = new AnimationOperation("C", ShapeType.OVAL,
            50, 440, 250, 120, 60, 0, 0, 255,
            70, 440, 370, 120, 60, 0, 170, 85);
    C4_ChangeColor = new AnimationOperation("C", ShapeType.OVAL,
            70, 440, 370, 120, 60, 0, 170, 85,
            80, 440, 370, 120, 60, 0, 255, 0);
    C5_Stable = new AnimationOperation("C", ShapeType.OVAL,
            80, 440, 370, 120, 60, 0, 255, 0,
            100, 440, 370, 120, 60, 0, 255, 0);
    C6_Scale = new AnimationOperation("C", ShapeType.OVAL,
            100, 440, 370, 120, 60, 0, 255, 0,
            120, 440, 370, 60, 60, 0, 255, 0);

    shapes = new ArrayList<>();
    shapes.add(rr);
    shapes.add(cc);
    ani = new TreeMap<>(Comparator.comparingInt(a -> a));
    ani.put(1, List.of(R1_Stable));
    ani.put(6, List.of(C1_Stable));
    ani.put(10, List.of(R2_Move));
    ani.put(20, List.of(C2_Move));
    ani.put(50, List.of(R3_Stable, C3_Double));
    ani.put(51, List.of(R4_Scale));
    ani.put(70, List.of(R5_Move, C4_ChangeColor));
    ani.put(75, List.of(R6_Scale));
    ani.put(80, List.of(C5_Stable));
    ani.put(100, List.of(C6_Scale));

    noAni = new TreeMap<>(Comparator.comparingInt(a -> a));
    noAni.put(1, List.of(R1_Stable));
    noAni.put(6, List.of(C1_Stable));
    noAni.put(80, List.of(C5_Stable));
  }


  /**
   * Test constructor.
   */
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


}