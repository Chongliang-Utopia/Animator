package cs5004.animator.model;

import java.util.List;
import java.util.Map;

/**
 * An Interface represents the model of the animator.
 * It includes all the methods that the model are supposed to return to the outside world.
 */
public interface IModel {
    /**
     * Return a copy of the shapes in the model.
     * @return a copy of the shapes in the model
     */
    Map<String, AbstractShape> getAllShape();

    /**
     * Return a copy of all the animations in the model.
     * @return a copy of all the animations in the model
     */
    Map<Integer, List<AbstractAnimation>> getAllAnimation();

    /**
     *
     * @param shape
     */
    void addShape(AbstractShape shape);


    void deleteShape(String shapeName);

    void addAnimation(AbstractAnimation animation);
}
