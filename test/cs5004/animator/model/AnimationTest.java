package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * A Junit test for the Animation classes, including AbstractAnimation, ChangeColorAnimation,
 * ScaleAnimation and MoveAnimation.
 */
public class AnimationTest {
  AbstractAnimation R1_Stable;
  AbstractAnimation R2_Move;
  AbstractAnimation R3_Stable;
  AbstractAnimation R4_Scale;
  AbstractAnimation R5_Move;

  AbstractAnimation C1_Stable;
  AbstractAnimation C2_Move;
  AbstractAnimation C3_Double;
  AbstractAnimation C4_ChangeColor;
  AbstractAnimation C5_Stable;

  AbstractShape r;
  AbstractShape c;

  /**
   * Construct shape and Animation objects for tests.
   */
  @Before
  public void setUp() {
    // Create shape.
    r = new Rectangle("R");
    c = new Oval("C");

    r = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234),
        new Position2D(200.0, 200.0),
        50.0, 100.0, 1, 100);
    c = new Oval("C", ShapeType.OVAL,
        new ColorType(0.0f, 0.0f, 1.0f),
        new Position2D(500.0, 100.0),
        60.0, 30.0, 6, 100);
    // Create Animation.
    R1_Stable = new AnimationOperation("R", ShapeType.RECTANGLE,
        1,200,200,50, 100, 255, 0,  0,
        10,  200, 200,50, 100, 255, 0,  0);
    R2_Move = new AnimationOperation("R", ShapeType.RECTANGLE,
        10, 200, 200, 50, 100, 255, 0,  0,
        50,  300, 300, 50, 100, 255, 0,  0);
    R3_Stable = new AnimationOperation("R", ShapeType.RECTANGLE,
        50,300, 300, 50, 100, 255, 0,  0,
        51,300, 300, 50, 100, 255, 0,  0);
    R4_Scale = new AnimationOperation("R", ShapeType.RECTANGLE,
        51, 300, 300, 50, 100, 255, 0,  0,
        70,  300, 300, 25, 100, 255, 0,  0);
    R5_Move = new AnimationOperation("R", ShapeType.RECTANGLE,
        70, 300, 300,25, 100, 255, 0,  0,
        100, 200, 200, 25, 100, 255, 0,  0);
    C1_Stable = new AnimationOperation("C", ShapeType.OVAL,
        6, 440, 70, 120, 60, 0, 0, 255,
        20, 440, 70, 120, 60, 0, 0, 255);
    C2_Move = new AnimationOperation("C", ShapeType.OVAL,
        20,440, 70, 120, 60,0, 0, 255,
        50, 440, 250, 120, 60, 0, 0, 255);
    C3_Double = new AnimationOperation("C", ShapeType.OVAL,
        50, 440, 250, 120, 60,0, 0, 255,
        70, 440, 370, 120, 60, 0, 170, 85);
    C4_ChangeColor = new AnimationOperation("C", ShapeType.OVAL,
        70, 440, 370,120, 60, 0, 170, 85,
        80, 440, 370, 120, 60, 0, 255, 0);
    C5_Stable = new AnimationOperation("C", ShapeType.OVAL,
        80, 440, 370, 120, 60, 0, 255, 0,
        100, 440, 370, 120, 60, 0, 255, 0);

  }

  /**
   * Test getShapeName.
   */
  @Test
  public void getShapeName1() {
    assertEquals("R", R2_Move.getShapeName());
  }

  /**
   * Test getShapeName.
   */
  @Test
  public void getShapeName2() {
    assertEquals("C", C3_Double.getShapeName());
  }

  /**
   * Test getStartTime.
   */
  @Test
  public void getStartTime1() {
    assertEquals(10, R2_Move.getStartTime());
  }

  /**
   * Test getStartTime.
   */
  @Test
  public void getStartTime2() {
    assertEquals(6, C1_Stable.getStartTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime1() {
    assertEquals(50, R2_Move.getEndTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime2() {
    assertEquals(80, C4_ChangeColor.getEndTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime3() {
    assertEquals(70, R4_Scale.getEndTime());
  }


  /**
   * Test runAnimation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void runAnimationInvalid() {
    R2_Move.runAnimation(4);
  }

  /**
   * Test runAnimation.
   */
  @Test
  public void runAnimation1() {
    AbstractShape moveShape1 = new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(250, 250),
        50.0, 100.0, 10, 50);
    assertEquals(moveShape1, R2_Move.runAnimation(30));
  }

  /**
   * Test runAnimation.
   */
  @Test
  public void runAnimation2() {
    AbstractShape moveShape1 = new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(1.0f, 0.0f, 0.0f),
        new Position2D(250.0, 250.0),
        50.0, 100.0, 1, 100);
    assertEquals(moveShape1, R2_Move.runAnimation(r, 30));
  }

  /**
   * Test runAnimation.
   */
  @Test
  public void runAnimation3() {
    AbstractShape expectedShape = new Oval("C", ShapeType.OVAL,
        new ColorType(0.0f, 0.5f, 0.5f),
        new Position2D(500.0, 370.0),
        60.0, 30.0, 6, 100);
    AbstractShape colorShape1 = C2_Move.runAnimation(c, 65);
    // Run change color animation.
    assertEquals(expectedShape.textRender(),
        changeColorAni3_C.runAnimation(colorShape1, 65).textRender());
  }

  /**
   * Test runAnimation.
   */
  @Test
  public void runAnimation4() {
    AbstractShape expectedShape = new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(1.0f, 0.0f, 0.0f),
        new Position2D(300.0, 300.0),
        38.2, 100.0, 1, 100);
    AbstractShape scaleShape = R2_Move.runAnimation(r, 60);
    // Run scale animation.
    assertEquals(expectedShape.textRender(),
        R4_Scale.runAnimation(scaleShape, 60).textRender());
  }

  /**
   * Test runAnimation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void runAnimation5() {
    AbstractShape expectedShape = new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(1.0f, 0.0f, 0.0f),
        new Position2D(300.0, 300.0),
        38.2, 100.0, 1, 100);
    AbstractShape scaleShape = R2_Move.runAnimation(r, 60);
    // When the shape has already disappeared
    assertEquals(expectedShape.textRender(),
        R4_Scale.runAnimation(scaleShape, 120).textRender());
  }

  /**
   * Test calculate state.
   */
  @Test
  public void calculateState1() {
    assertEquals(250, R2_Move.calculateState(
        200, 300, 30), 0.1);
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString1() {
    assertEquals("Shape R move from (200.0,200.0) to "
        + "(300.0,300.0) from t=10 to t=50\n", R2_Move.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString2() {
    assertEquals("Shape C changes color from (0.0,0.0,1.0) to "
        + "(0.0,1.0,0.0) from t=50 to t=80\n", changeColorAni3_C.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString3() {
    assertEquals("Shape R scales from Width: 50.0 Height: 100.0 to"
        + " Width: 25.0 Height: 100.0 from t=51 to t=70\n", R4_Scale.toString());
  }
}