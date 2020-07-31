package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A Junit test class for ModelImpl. It includes tests for all the methods of the ModelImpl class.
*/
public class ModelImplTest {
    IModel model1;
    IModel model11;
    Map<String, AbstractShape> allShapes1;
    Map<Integer, List<AbstractAnimation>> allAnimations1;

    /**
     * Construct shape, model and Animation objects for tests.
     */
    @Before
    public void setUp() {
        model11 = new ModelImpl();
        allShapes1 = new HashMap<>();
        allShapes1.put("R", new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        allShapes1.put("C", new Oval("C", ShapeType.OVAL,
                new ColorType(0.0f, 0.0f, 1.0f),
                new Position2D(500.0, 100.0),
                60.0, 30.0, 6, 100));
        allAnimations1 = new TreeMap<>(Comparator.comparingInt(a -> a));
        allAnimations1.put(10, List.of(new MoveAnimation(AnimationType.MOVE,
                "R", 10, 50, new Position2D(200.0, 200.0),
                new Position2D(300.0, 300.0))));
        allAnimations1.put(20, List.of(new MoveAnimation(AnimationType.MOVE,
                "C", 20, 70, new Position2D(500.0, 100.0),
                new Position2D(500.0, 400.0))));
        allAnimations1.put(50, List.of(new ChangeColorAnimation(AnimationType.CHANGECOLOR,
                "C", 50, 80,
                new ColorType(0.0f, 0.0f, 1.0f),
                new ColorType(0.0f, 1.0f, 0.0f))));
        // Add shapes
        model11.addShape(new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        model11.addShape(new Oval("C", ShapeType.OVAL,
                new ColorType(0.0f, 0.0f, 1.0f),
                new Position2D(500.0, 100.0),
                60.0, 30.0, 6, 100));
        // Add animations
        model11.addAnimation(new ChangeColorAnimation(AnimationType.CHANGECOLOR,
                "C", 50, 80,
                new ColorType(0.0f, 0.0f, 1.0f),
                new ColorType(0.0f, 1.0f, 0.0f)));
        model11.addAnimation(new MoveAnimation(AnimationType.MOVE,
                "C", 20, 70, new Position2D(500.0, 100.0),
                new Position2D(500.0, 400.0)));
        model11.addAnimation(new MoveAnimation(AnimationType.MOVE,
                "R", 10, 50, new Position2D(200.0, 200.0),
                new Position2D(300.0, 300.0)));

        model1 = new ModelImpl(allShapes1, allAnimations1);
    }

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor1() {
        IModel model = new ModelImpl(allShapes1, allAnimations1);
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
        assertEquals(allShapes1, model11.getAllShape());
    }

    /**
     * Test when the two allShapes are not equal.
     */
    @Test
    public void testGetAllShape2() {
        Map<String, AbstractShape> allShapesNotEqual = new HashMap<>();
        allShapesNotEqual.put("R", new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
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
        assertEquals(new HashMap<String, AbstractShape>(),
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
     * Test when the two allAnimations are not equal.
     */
    @Test
    public void testGetAllAnimationSortedByTime2() {
        Map<Integer, List<AbstractAnimation>> allAnimationsNotEqual = new TreeMap<>();
        allAnimationsNotEqual.put(10, List.of(new MoveAnimation(AnimationType.MOVE,
                "R", 10, 50, new Position2D(200.0, 200.0),
                new Position2D(300.0, 300.0))));
        assertNotEquals(allAnimationsNotEqual, model1.getAllAnimationSortedByTime());
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
    public void testAddShape1() {
        assertEquals(allShapes1, model11.getAllShape());
    }

    /**
     * Test add shape.
     */
    @Test
    public void testAddShape2() {
        Map<String, AbstractShape> allShapes2 = new HashMap<>();
        allShapes2.put("R", new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        IModel model2 = new ModelImpl();
        model2.addShape(new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        assertEquals(allShapes2, model2.getAllShape());
    }

    /**
     * Test add shape when the name of the shape already exist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddShapeInvalid1() {
        model1.addShape(new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
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
        assertEquals(allAnimations1, model11.getAllAnimationSortedByTime());
    }

    /**
     * test add invalid animation. when the two same type animation overlap and have
     * different instructions.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimationInvalid1() {
        model1.addAnimation(new MoveAnimation(AnimationType.MOVE,
                "C", 40, 70, new Position2D(100.0, 100.0),
                new Position2D(600.0, 400.0)));
    }

    /**
     * test for getAllShapeAtGivenTime
     */
    @Test
    public void testGetAllShapeAtGivenTime1() {
        Map<String, AbstractShape> allShapesAtGivenTime = new HashMap<>();
        assertEquals(allShapesAtGivenTime, model1.getAllShapeAtGivenTime(0));
    }

    /**
     * test for getAllShapeAtGivenTime
     */
    @Test
    public void testGetAllShapeAtGivenTime2() {
        Map<String, AbstractShape> allShapesAtGivenTime = new HashMap<>();
        allShapesAtGivenTime.put("R", new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        assertEquals(allShapesAtGivenTime, model1.getAllShapeAtGivenTime(3));
    }

    /**
     * test for getAllShapeAtGivenTime
     */
    @Test
    public void testGetAllShapeAtGivenTime3() {
        Map<String, AbstractShape> allShapesAtGivenTime = new HashMap<>();
        allShapesAtGivenTime.put("R", new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        allShapesAtGivenTime.put("C", new Oval("C", ShapeType.OVAL,
                new ColorType(0.0f, 0.0f, 1.0f),
                new Position2D(500.0, 100.0),
                60.0, 30.0, 6, 100));
        assertEquals(allShapesAtGivenTime, model1.getAllShapeAtGivenTime(10));
    }

    /**
     * test for getAllShapeAtGivenTime
     */
    @Test
    public void testGetAllShapeAtGivenTime4() {
        Map<String, AbstractShape> allShapesAtGivenTime = new HashMap<>();
        assertEquals(allShapesAtGivenTime, model1.getAllShapeAtGivenTime(105));
    }

    /**
     * test for getAllSortedAnimationAtGivenTime.
     */
    @Test
    public void testGetAllSortedAnimationAtGivenTime() {
        Map<Integer, List<AbstractAnimation>> allAnimationsAtGivenTime = new TreeMap<>();
        allAnimationsAtGivenTime.put(10, List.of(new MoveAnimation(AnimationType.MOVE,
                "R", 10, 50, new Position2D(200.0, 200.0),
                new Position2D(300.0, 300.0))));
        assertEquals(allAnimationsAtGivenTime, model1.getAllSortedAnimationAtGivenTime(15));
    }

    /**
     * test for getAllSortedAnimationAtGivenTime.
     */
    @Test
    public void testGetAllSortedAnimationAtGivenTime2() {
        Map<Integer, List<AbstractAnimation>> allAnimationsAtGivenTime = new TreeMap<>();
        allAnimationsAtGivenTime.put(10, List.of(new MoveAnimation(AnimationType.MOVE,
                "R", 10, 50, new Position2D(200.0, 200.0),
                new Position2D(300.0, 300.0))));
        allAnimationsAtGivenTime.put(20, List.of(new MoveAnimation(AnimationType.MOVE,
                "C", 20, 70, new Position2D(500.0, 100.0),
                new Position2D(500.0, 400.0))));
        assertEquals(allAnimationsAtGivenTime, model1.getAllSortedAnimationAtGivenTime(35));
    }

    /**
     * test for getAllSortedAnimationAtGivenTime.
     */
    @Test
    public void testGetAllSortedAnimationAtGivenTime3() {
        Map<Integer, List<AbstractAnimation>> allAnimationsAtGivenTime = new TreeMap<>();
        assertEquals(allAnimationsAtGivenTime, model1.getAllSortedAnimationAtGivenTime(1));
    }

    /**
     * test for getAllSortedAnimationAtGivenTime.
     */
    @Test
    public void testGetAllSortedAnimationAtGivenTime4() {
        assertEquals(allAnimations1, model1.getAllSortedAnimationAtGivenTime(80));
    }

    /**
     * test for getAllSortedAnimationAtGivenTime.
     */
    @Test
    public void testGetAllSortedAnimationAtGivenTime5() {
        assertEquals(0, model1.getAllSortedAnimationAtGivenTime(180).size());
    }

    /**
     * Test toString.
     */
    @Test
    public void testToString1() {
        String expected = "Shapes:\n"
                + "Name: R\nType: rectangle\nMin corner: (200.0,200.0), Width: 50.0, "
                + "Height: 100.0, Color: (1.0,0.0,0.0)\nAppears at t=1\nDisappears at t=100\n\n"
                + "Name: C\nType: oval\nCenter: (500.0,100.0), X radius: 60.0, "
                + "Y radius: 30.0, Color: (0.0,0.0,1.0)\nAppears at t=6\nDisappears at t=100\n\n"
                + "Shape R move from (200.0,200.0) to (300.0,300.0) from t=10 to t=50\n"
                + "Shape C move from (500.0,100.0) to (500.0,400.0) from t=20 to t=70\n"
                + "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=50 to t=80\n";
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
                + "Height: 100.0, Color: (1.0,0.0,0.0)\nAppears at t=1\nDisappears at t=100\n\n";
        IModel model11 = new ModelImpl();
        model11.addShape(new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(200.0, 200.0),
                50.0, 100.0, 1, 100));
        assertEquals(expected, model11.toString());
    }
}