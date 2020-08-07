package cs5004.animator.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cs5004.animator.model.AnimationOperation;
import cs5004.animator.model.ColorType;
import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IShape;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.model.Oval;
import cs5004.animator.model.Position2D;
import cs5004.animator.model.Rectangle;
import cs5004.animator.model.Screen;
import cs5004.animator.model.ShapeType;
import cs5004.animator.view.TextView;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for the controller.
 */
public class ControllerImplTest {
  IModel model1;
  List<IShape> allShapes1;
  Map<Integer, List<IAnimation>> allAnimations1;
  IAnimation rStable1;
  IAnimation rMove2;
  IAnimation rStable3;
  IAnimation rScale4;
  IAnimation rMove5;
  IAnimation cStable1;
  IAnimation cMove2;
  IAnimation cDouble3;
  IAnimation cChangeColor4;
  IAnimation cStable5;
  IShape rr;
  IShape cc;
  IController controller;


  /**
   * Construct shape, model and Animation objects for tests.
   */
  @Before
  public void setUp() {
    rr = new Rectangle("R", ShapeType.RECTANGLE, new ColorType(255, 0, 0),
        new Position2D(200, 200), 50, 100, 1, 100);
    cc = new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
        new Position2D(440, 70), 120, 60, 6, 100);
    // Create Animation.
    rStable1 = new AnimationOperation("R", ShapeType.RECTANGLE,
        1,200,200,50, 100, 255, 0,  0,
        10,  200, 200,50, 100, 255, 0,  0);
    rMove2 = new AnimationOperation("R", ShapeType.RECTANGLE,
        10, 200, 200, 50, 100, 255, 0,  0,
        50,  300, 300, 50, 100, 255, 0,  0);
    rStable3 = new AnimationOperation("R", ShapeType.RECTANGLE,
        50,300, 300, 50, 100, 255, 0,  0,
        51,300, 300, 50, 100, 255, 0,  0);
    rScale4 = new AnimationOperation("R", ShapeType.RECTANGLE,
        51, 300, 300, 50, 100, 255, 0,  0,
        70,  300, 300, 25, 100, 255, 0,  0);
    rMove5 = new AnimationOperation("R", ShapeType.RECTANGLE,
        70, 300, 300,25, 100, 255, 0,  0,
        100, 200, 200, 25, 100, 255, 0,  0);
    cStable1 = new AnimationOperation("C", ShapeType.OVAL,
        6, 440, 70, 120, 60, 0, 0, 255,
        20, 440, 70, 120, 60, 0, 0, 255);
    cMove2 = new AnimationOperation("C", ShapeType.OVAL,
        20,440, 70, 120, 60,0, 0, 255,
        50, 440, 250, 120, 60, 0, 0, 255);
    cDouble3 = new AnimationOperation("C", ShapeType.OVAL,
        50, 440, 250, 120, 60,0, 0, 255,
        70, 440, 370, 120, 60, 0, 170, 85);
    cChangeColor4 = new AnimationOperation("C", ShapeType.OVAL,
        70, 440, 370,120, 60, 0, 170, 85,
        80, 440, 370, 120, 60, 0, 255, 0);
    cStable5 = new AnimationOperation("C", ShapeType.OVAL,
        80, 440, 370, 120, 60, 0, 255, 0,
        100, 440, 370, 120, 60, 0, 255, 0);

    allShapes1 = new ArrayList<>();
    allShapes1.add(rr);
    allShapes1.add(cc);
    allAnimations1 = new TreeMap<>(Comparator.comparingInt(a -> a));
    allAnimations1.put(1, List.of(rStable1));
    allAnimations1.put(6, List.of(cStable1));
    allAnimations1.put(10, List.of(rMove2));
    allAnimations1.put(20, List.of(cMove2));
    allAnimations1.put(50, List.of(rStable3, cDouble3));
    allAnimations1.put(51, List.of(rScale4));
    allAnimations1.put(70, List.of(rMove5, cChangeColor4));
    allAnimations1.put(80, List.of(cStable5));
    model1 = new ModelImpl(allShapes1, allAnimations1,
        new Screen(0, 0, 1000, 1000));
    controller = new TextViewControllerImpl(model1, new TextView(1));
  }

  /**
   * Test constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    IController controller1 = new ImageViewControllerImpl(null, new TextView(1));
  }

  /**
   * Test constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    IController controller1 = new ImageViewControllerImpl(model1, null);
  }


  /**
   * Test run.
   */
  @Test(expected = IllegalStateException.class)
  public void testRun1() throws IOException {
    StringBuilder sb = new StringBuilder();
    controller.run(null);
  }

  /**
   * Test run.
   */
  @Test
  public void testRun2() throws IOException {
    StringBuilder sb = new StringBuilder();
    String expected = "Create rectangle R with color (255,0,0) with corner at (200.0,200.0), "
        + "width: 50 and height 100\n"
        + "Create oval C with color (0,0,255) with center at (440.0,70.0), radius: 120 and 60\n"
        + "\nR appears at time t=1 and disappear at time t=100\n"
        + "C appears at time t=6 and disappear at time t=100\n\n"
        + "R moves from (200.0,200.0) to (300.0,300.0) from time t=10 to t=50\n"
        + "C moves from (440.0,70.0) to (440.0,250.0) from time t=20 to t=50\n"
        + "C changes color from (0,0,255) to (0,170,85) from time t=50 to t=70\n"
        + "C moves from (440.0,250.0) to (440.0,370.0) from time t=50 to t=70\n"
        + "R changes width from 50 to 25 from time t=51 to t=70\n"
        + "R moves from (300.0,300.0) to (200.0,200.0) from time t=70 to t=100\n"
        + "C changes color from (0,170,85) to (0,255,0) from time t=70 to t=80\n";
    controller.run(sb);
    assertEquals(expected, sb.toString());
  }
}