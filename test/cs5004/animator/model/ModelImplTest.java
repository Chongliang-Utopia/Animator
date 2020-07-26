package cs5004.animator.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A Junit test class for ModelImpl
 */
public class ModelImplTest {
    IModel model1;
    IModel model11;
    Map<String, AbstractShape> allShapes1;
    Map<Integer, List<AbstractAnimation>> allAnimations1;

    @org.junit.Before
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
     * Test if getAllShapes get valid shapes.
     */
    @org.junit.Test
    public void testGetAllShape1() {
        assertEquals(allShapes1, model11.getAllShape());
    }

    /**
     * Test when the two allShapes are not equal.
     */
    @org.junit.Test
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
    @org.junit.Test
    public void testGetAllShape3() {
        IModel model = new ModelImpl();
        assertEquals(new HashMap<String, AbstractShape>(),
                model.getAllShape());
    }

    /**
     * Test if getAllAnimation get valid animations.
     */
    @org.junit.Test
    public void testGetAllAnimationSortedByTime1() {
        assertEquals(allAnimations1, model1.getAllAnimationSortedByTime());
    }

    /**
     * Test when the two allAnimations are not equal.
     */
    @org.junit.Test
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
    @org.junit.Test
    public void testGetAllAnimationSortedByTime3() {
        IModel model = new ModelImpl();
        assertEquals(new TreeMap<Integer, List<AbstractAnimation>>(),
                model.getAllAnimationSortedByTime());

    }

    /**
     * Test add shape.
     */
    @org.junit.Test
    public void testAddShape1() {
        assertEquals(allShapes1, model11.getAllShape());
    }

    /**
     * Test add shape.
     */
    @org.junit.Test
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
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testAddShapeInvalid1() {
        model1.addShape(new Rectangle("R", ShapeType.RECTANGLE,
                new ColorType(1.0f, 0.0f, 0.0f),
                new Position2D(300.0, 200.0),
                50.0, 100.0, 1, 100));
    }

    /**
     * Test deleteShape.
     */
    @org.junit.Test
    public void deleteShape1() {
        model1.deleteShape("R");
        assertEquals(1, model1.getAllShape().size());
    }

    /**
     * Test deleteShape.
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void deleteShape2() {
        model1.deleteShape("Q");
    }

    /**
     * test add animation.
     */
    @org.junit.Test
    public void testAddAnimation1() {
        assertEquals(allAnimations1, model11.getAllAnimationSortedByTime());
    }

    /**
     * test add invalid animation. when the two same type animation overlap and have
     * different instructions.
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testAddAnimationInvalid1() {
        model1.addAnimation(new MoveAnimation(AnimationType.MOVE,
                "C", 40, 70, new Position2D(100.0, 100.0),
                new Position2D(600.0, 400.0)));
    }
}