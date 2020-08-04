package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import cs5004.animator.util.AnimationReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * A Junit test class for ModelImpl. It includes tests for all the methods of the ModelImpl class.
 */
public class ModelImplTest {
  IModel model1;
  IModel model2;
  List<AbstractShape> allShapes1;
  List<AbstractShape> allShapes2;
  Map<Integer, List<AbstractAnimation>> allAnimations1;
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
  AbstractShape rr;
  AbstractShape cc;

  /**
   * Construct shape, model and Animation objects for tests.
   */
  @Before
  public void setUp() {
    r = new Rectangle("R");
    c = new Oval("C");
    rr = new Rectangle("R", ShapeType.RECTANGLE, new ColorType(255, 0, 0),
        new Position2D(200, 200), 50, 100, 1, 100);
    cc = new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
        new Position2D(440, 70), 120, 60, 6, 100);
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

    allShapes1 = new ArrayList<>();
    allShapes1.add(r);
    allShapes1.add(c);
    allShapes2 = new ArrayList<>();
    allShapes2.add(rr);
    allShapes2.add(cc);
    allAnimations1 = new TreeMap<>(Comparator.comparingInt(a -> a));
    allAnimations1.put(1, List.of(R1_Stable));
    allAnimations1.put(6, List.of(C1_Stable));
    allAnimations1.put(10, List.of(R2_Move));
    allAnimations1.put(20, List.of(C2_Move));
    allAnimations1.put(50, List.of(R3_Stable, C3_Double));
    allAnimations1.put(51, List.of(R4_Scale));
    allAnimations1.put(70, List.of(R5_Move, C4_ChangeColor));
    allAnimations1.put(80, List.of(C5_Stable));
    model1 = new ModelImpl(allShapes1, allAnimations1);
    model2 = new ModelImpl(allShapes2, allAnimations1);
  }

  /**
   * Test constructor.
   */
  @Test
  public void testConstructor1() {
    try {
      IModel model = new ModelImpl(allShapes1, allAnimations1);
    } catch (IllegalArgumentException e) {
      fail();
    }

  }

  /**
   * Test constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    IModel model = new ModelImpl(null, allAnimations1);
  }

  /**
   * Test constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    IModel model = new ModelImpl(allShapes1, null);
  }

  /**
   * Test if getAllShapes get valid shapes.
   */
  @Test
  public void testGetAllShape1() {
    assertEquals(allShapes1, model1.getAllShape());
  }

  /**
   * Test when the two allShapes are not equal.
   */
  @Test
  public void testGetAllShape2() {
    Map<String, AbstractShape> allShapesNotEqual = new HashMap<>();
    allShapesNotEqual.put("R", new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 2, 3),
        new Position2D(200.0, 200.0),
        50.0, 100.0, 1, 100));
    assertNotEquals(allShapesNotEqual, model1.getAllShape());
  }

  /**
   * Test when there's no shapes.
   */
  @Test
  public void testGetAllShape3() {
    IModel model = new ModelImpl();
    assertEquals(new ArrayList<>(),
        model.getAllShape());
  }

  /**
   * Test if getAllAnimation get valid animations.
   */
  @Test
  public void testGetAllAnimationSortedByTime1() {
    assertEquals(allAnimations1, model1.getAllAnimationSortedByTime());
  }

  /**
   * Test getAllAnimationSortedByTime.
   */
  @Test
  public void testGetAllAnimationSortedByTime2() {
    assertEquals(allAnimations1, model1.getAllAnimationSortedByTime());
  }

  /**
   * Test when there's no animations.
   */
  @Test
  public void testGetAllAnimationSortedByTime3() {
    IModel model = new ModelImpl();
    assertEquals(new TreeMap<Integer, List<AbstractAnimation>>(),
        model.getAllAnimationSortedByTime());

  }

  /**
   * Test add shape.
   */
  @Test
  public void testAddShape2() {
    IModel model2 = new ModelImpl();
    model2.addShape(r);
    model2.addShape(c);
    assertEquals(allShapes1, model2.getAllShape());
  }

  /**
   * Test add shape when the name of the shape already exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddShapeInvalid1() {
    model1.addShape(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 255),
        new Position2D(300.0, 200.0),
        50.0, 100.0, 1, 100));
  }

  /**
   * Test deleteShape.
   */
  @Test
  public void deleteShape1() {
    model1.deleteShape("R");
    assertEquals(1, model1.getAllShape().size());
  }

  /**
   * Test deleteShape.
   */
  @Test(expected = IllegalArgumentException.class)
  public void deleteShape2() {
    model1.deleteShape("Q");
  }

  /**
   * test add animation.
   */
  @Test
  public void testAddAnimation1() {
    assertEquals(allAnimations1, model1.getAllAnimationSortedByTime());
  }

  /**
   * test add invalid animation. when the two same type animation overlap and have different
   * instructions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddAnimationInvalid1() {
    //when the two same type animation overlap and have different instructions.
    model1.addAnimation(new AnimationOperation("R", ShapeType.RECTANGLE,
        15, 250, 200, 50, 100, 255, 0,  0,
        50,  100, 300, 50, 100, 255, 0,  0));
  }



  /**
   * Test toString.
   */
  @Test
  public void testToString1() {
    String expected = "Shapes:\n"
        + "Name: R\nType: rectangle\nMin corner: (0.0,0.0), Width: 1.0, "
        + "Height: 1.0, Color: (0,0,0)\nAppears at t=2147483647\nDisappears at t=-2147483648\n\n"
        + "Name: C\nType: oval\nCenter: (0.0,0.0), X radius: 1.0, "
        + "Y radius: 1.0, Color: (0,0,0)\nAppears at t=2147483647\nDisappears at t=-2147483648\n\n"
        + "Shape R move from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C move from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
        + "Shape C move from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
        + "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70\n"
        + "Shape R scales from Width: 50 Height: 100 to Width: 25 Height: 100 from t=51 to t=70\n"
        + "Shape R move from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
        + "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80\n";
    assertEquals(expected, model1.toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString2() {
    String expected = "Shapes:\n";
    assertEquals(expected, new ModelImpl().toString());
  }

  /**
   * Test toString.
   */
  @Test
  public void testToString3() {
    String expected = "Shapes:\n"
        + "Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
        + "Height: 100.0, Color: (255,0,0)\nAppears at t=1\nDisappears at t=100\n\n";
    IModel model11 = new ModelImpl();
    model11.addShape(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(200.0, 200.0),
        50.0, 100.0, 1, 100));
    assertEquals(expected, model11.toString());
  }

  /**
   * test getUpdatedShapeAtGivenTime
   */
  @Test
  public void testGetUpdatedShapeAtGivenTime1() {
    List<IReadOnlyShapes> expected = new ArrayList<>();
    expected.add(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(200, 200), 50, 100, 1, 10));
    List<IReadOnlyShapes> actual = model2.getUpdatedShapeAtGivenTime(1);
    assertEquals(expected, actual);
  }

  /**
   * test getUpdatedShapeAtGivenTime
   */
  @Test
  public void testGetUpdatedShapeAtGivenTime2() {
    List<IReadOnlyShapes> expected = new ArrayList<>();
    expected.add(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(250, 250), 50, 100, 10, 50));
    expected.add(new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
        new Position2D(440, 130), 120, 60, 20, 50));
    List<IReadOnlyShapes> actual = model2.getUpdatedShapeAtGivenTime(30);
    System.out.println(actual.get(0).textRender());
    System.out.println(actual.get(1).textRender());
    assertEquals(expected, actual);
  }

  /**
   * test getUpdatedShapeAtGivenTime
   */
  @Test
  public void testGetUpdatedShapeAtGivenTime3() {
    List<IReadOnlyShapes> expected = new ArrayList<>();
    expected.add(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(250, 250), 50, 100, 10, 50));
    expected.add(new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
        new Position2D(440, 130), 120, 60, 20, 50));
    List<IReadOnlyShapes> actual = model2.getUpdatedShapeAtGivenTime(120);
    assertEquals(0, actual.size());
  }

  /**
   * test getUpdatedShapeAtGivenTime
   */
  @Test
  public void testGetUpdatedShapeAtGivenTime4() {
    List<IReadOnlyShapes> expected = new ArrayList<>();
    expected.add(new Rectangle("R", ShapeType.RECTANGLE,
        new ColorType(255, 0, 0),
        new Position2D(250, 250), 50, 100, 10, 50));
    expected.add(new Oval("C", ShapeType.OVAL, new ColorType(0, 0, 255),
        new Position2D(440, 130), 120, 60, 20, 50));
    List<IReadOnlyShapes> actual = model2.getUpdatedShapeAtGivenTime(0);
    assertEquals(0, actual.size());
  }

  /**
   * Test builder
   */
  @Test
  public void testBuilder1() throws IOException {
    IModel model = null;
    String inputFile = "smalldemo.txt";
    try {
      InputStream inputStream = new FileInputStream(inputFile);
      model = AnimationReader.parseFile(new InputStreamReader(inputStream),
          new ModelImpl.Builder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String expected = "Shapes:\n"
        + "Name: R\nType: rectangle\nMin corner: (0.0,0.0), Width: 1.0, "
        + "Height: 1.0, Color: (0,0,0)\nAppears at t=1\nDisappears at t=100\n\n"
        + "Name: C\nType: oval\nCenter: (0.0,0.0), X radius: 1.0, "
        + "Y radius: 1.0, Color: (0,0,0)\nAppears at t=6\nDisappears at t=100\n\n"
        + "Shape R move from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
        + "Shape C move from (440.0,70.0) to (440.0,250.0) from t=20 to t=50\n"
        + "Shape C move from (440.0,250.0) to (440.0,370.0) from t=50 to t=70\n"
        + "Shape C changes color from (0,0,255) to (0,170,85) from t=50 to t=70\n"
        + "Shape R scales from Width: 50 Height: 100 to Width: 25 Height: 100 from t=51 to t=70\n"
        + "Shape R move from (300.0,300.0) to (200.0,200.0) from t=70 to t=100\n"
        + "Shape C changes color from (0,170,85) to (0,255,0) from t=70 to t=80\n";
    assertEquals(expected, model.toString());
  }
}