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
     * @return a copy of the shapes in the model, use shape name as the key for the map.
     */
    Map<String, AbstractShape> getAllShape();

    /**
     * Return a copy of the shapes in the model at Given time.
     * All the shapes that are visible at the given time will be included in the map.
     * @return a copy of the shapes in the model at Given time.
     */
    Map<String, AbstractShape> getAllShapeAtGivenTime(int time);

    /**
     * Return a copy of all the animations in the model sorted by time.
     * @return a copy of all the animations in the model sorted by time, use start time of
     * the animation as the key for the map, it stores a list of animation under the specific time.
     */
    Map<Integer, List<AbstractAnimation>> getAllAnimationSortedByTime();

    /**
     * Return a sorted copy of all the animations that has started at given time.
     * @return sorted copy of all the animations that has started at given time, use start time of
     * the animation as the key for the map, it stores a list of animation under the specific time.
     */
    Map<Integer, List<AbstractAnimation>> getAllSortedAnimationAtGivenTime(int time);

    /**
     * Add a shape to the model.
     * @param shape the shape to add in the model
     * @throws IllegalArgumentException if the shape cannot be added, such as the shape has an
     * identical name with an existing shape in the model
     */
    void addShape(AbstractShape shape) throws IllegalArgumentException;

    /**
     * Delete a shape in the model
     * @param shapeName the name of the shape to delete
     * @throws IllegalArgumentException if the delete operation cannot be successful when the
     * shape does not exist in the model
     */
    void deleteShape(String shapeName) throws IllegalArgumentException;

    /**
     * Add animation to the model
     * @param animation the animation to add in the model
     * @throws IllegalArgumentException if the animation cannot be added, such as the shape of the
     * animation does not exist, or the animation has a conflict with an existing animation
     */
    void addAnimation(AbstractAnimation animation) throws IllegalArgumentException;
}
