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
  AbstractAnimation moveAni1;
  AbstractAnimation moveAni2;
  AbstractAnimation moveAni3;
  AbstractAnimation changeColorAni;
  AbstractAnimation scaleAni;
  AbstractShape r;
  AbstractShape c;

  /**
   * Construct shape and Animation objects for tests.
   */
  @Before
  public void setUp() {
    // Create shape.
    r = new Rectangle("R", ShapeType.RECTANGLE,
            new ColorType(255, 139, 234),
        new Position2D(200.0, 200.0),
        50.0, 100.0, 1, 100);
    c = new Oval("C", ShapeType.OVAL,
        new ColorType(0.0f, 0.0f, 1.0f),
        new Position2D(500.0, 100.0),
        60.0, 30.0, 6, 100);
    // Create Animation.
    moveAni1 = new MoveAnimation(AnimationType.MOVE, "R", 10, 50,
        new Position2D(200.0, 200.0), new Position2D(300.0, 300.0));
    moveAni2 = new MoveAnimation(AnimationType.MOVE, "C", 20, 70,
        new Position2D(500.0, 100.0), new Position2D(500.0, 400.0));
    changeColorAni = new
        ChangeColorAnimation(AnimationType.CHANGECOLOR,
        "C", 50, 80,
        new ColorType(0.0f, 0.0f, 1.0f),
        new ColorType(0.0f, 1.0f, 0.0f));
    scaleAni = new ScaleAnimation(AnimationType.SCALE, "R", 51, 70,
        List.of(50.0, 100.0), List.of(25.0, 100.0));
    moveAni3 = new MoveAnimation(AnimationType.MOVE, "R", 70, 100,
        new Position2D(300.0, 300.0), new Position2D(200.0, 200.0));
  }

  /**
   * Test getShapeName.
   */
  @Test
  public void getShapeName1() {
    assertEquals("R", moveAni1.getShapeName());
  }

  /**
   * Test getShapeName.
   */
  @Test
  public void getShapeName2() {
    assertEquals("C", changeColorAni.getShapeName());
  }

  /**
   * Test getStartTime.
   */
  @Test
  public void getStartTime1() {
    assertEquals(10, moveAni1.getStartTime());
  }

  /**
   * Test getStartTime.
   */
  @Test
  public void getStartTime2() {
    assertEquals(50, changeColorAni.getStartTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime1() {
    assertEquals(50, moveAni1.getEndTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime2() {
    assertEquals(80, changeColorAni.getEndTime());
  }

  /**
   * Test getEndTime.
   */
  @Test
  public void getEndTime3() {
    assertEquals(70, scaleAni.getEndTime());
  }

  /**
   * Test getAnimationType.
   */
  @Test
  public void getAnimationType1() {
    assertEquals(AnimationType.MOVE, moveAni1.getAnimationType());
  }

  /**
   * Test getAnimationType.
   */
  @Test
  public void getAnimationType2() {
    assertEquals(AnimationType.CHANGECOLOR, changeColorAni.getAnimationType());
  }

  /**
   * Test getAnimationType.
   */
  @Test
  public void getAnimationType3() {
    assertEquals(AnimationType.SCALE, scaleAni.getAnimationType());
  }

  /**
   * Test runAnimation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void runAnimationInvalid() {
    moveAni1.runAnimation(null, 50);
  }

  /**
   * Test runAnimation.
   */
  @Test
  public void runAnimation1() {
    AbstractShape moveShape1 = new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(1.0f, 0.0f, 0.0f),
        new Position2D(300.0, 300.0),
        50.0, 100.0, 1, 100);
    assertEquals(moveShape1, moveAni1.runAnimation(r, 50));
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
    assertEquals(moveShape1, moveAni1.runAnimation(r, 30));
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
    AbstractShape colorShape1 = moveAni2.runAnimation(c, 65);
    // Run change color animation.
    assertEquals(expectedShape.textRender(),
        changeColorAni.runAnimation(colorShape1, 65).textRender());
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
    AbstractShape scaleShape = moveAni1.runAnimation(r, 60);
    // Run scale animation.
    assertEquals(expectedShape.textRender(),
        scaleAni.runAnimation(scaleShape, 60).textRender());
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
    AbstractShape scaleShape = moveAni1.runAnimation(r, 60);
    // When the shape has already disappeared
    assertEquals(expectedShape.textRender(),
        scaleAni.runAnimation(scaleShape, 120).textRender());
  }

  /**
   * Test calculate state.
   */
  @Test
  public void calculateState1() {
    assertEquals(250, moveAni1.calculateState(
        200, 300, 30), 0.1);
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString1() {
    assertEquals("Shape R move from (200.0,200.0) to "
        + "(300.0,300.0) from t=10 to t=50\n", moveAni1.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString2() {
    assertEquals("Shape C changes color from (0.0,0.0,1.0) to "
        + "(0.0,1.0,0.0) from t=50 to t=80\n", changeColorAni.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString3() {
    assertEquals("Shape R scales from Width: 50.0 Height: 100.0 to"
        + " Width: 25.0 Height: 100.0 from t=51 to t=70\n", scaleAni.toString());
  }
}