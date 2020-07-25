package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class represent the implementation of the IModel, a concrete class as the model of
 * the animator.  It includes all the methods that the model are supposed to
 * return to the outside world.
 */
public class modelImpl implements IModel {
    // Use shape name as the key for the map.
    private Map<String, AbstractShape> allShapes;
    // Use start time of the animation as the key for the map.
    // It stores a list of animation under the specific time.
    private Map<Integer, List<AbstractAnimation>> allAnimations;

    /**
     * Construct a modelImpl class with the shapes and animations.
     * @param allShapes shapes to include in the model
     * @param allAnimations animations to include in the model
     * @throws IllegalArgumentException if the parameter is null
     */
    private modelImpl(Map<String, AbstractShape> allShapes,
                      Map<Integer, List<AbstractAnimation>> allAnimations) throws
            IllegalArgumentException{
        if (allAnimations == null || allShapes == null) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.allShapes = allShapes;
        this.allAnimations = allAnimations;
    }

    /**
     * Return a copy of the shapes in the model.
     * @return a copy of the shapes in the model, use shape name as the key for the map.
     */
    @Override
    public Map<String, AbstractShape> getAllShape() {
        return new HashMap<>(allShapes);
    }

    /**
     * Return a copy of all the animations in the model.
     * @return a copy of all the animations in the model, use start time of the animation as the
     * key for the map, it stores a list of animation under the specific time.
     */
    @Override
    public Map<Integer, List<AbstractAnimation>> getAllAnimation() {
        return new HashMap<>(allAnimations);
    }

    /**
     * Add a shape to the model.
     * @param shape the shape to add in the model
     * @throws IllegalArgumentException if the shape cannot be added, such as the shape has an
     * identical name with an existing shape in the model
     */
    @Override
    public void addShape(AbstractShape shape) throws IllegalArgumentException{
        if (allShapes.containsKey(shape.getName())) {
            throw new IllegalArgumentException(
                    "Cannot create add a shape with an existing shape name");
        }
        allShapes.put(shape.getName(), shape);
    }


    /**
     * Delete a shape in the model
     * @param shapeName the name of the shape to delete
     * @throws IllegalArgumentException if the delete operation cannot be successful when the
     * shape does not exist in the model
     */
    @Override
    public void deleteShape(String shapeName) throws IllegalArgumentException{
        if (!allShapes.containsKey(shapeName)) {
            throw new IllegalArgumentException("Shape not found.");
        }
        // Remove shape.
        allShapes.remove(shapeName);
        // Remove corresponding animations.
        for (Map.Entry<Integer, List<AbstractAnimation>> entry : allAnimations.entrySet()) {
            List<AbstractAnimation> curAnimations = entry.getValue();
            curAnimations.removeIf(animation -> animation.getShapeName().equals(shapeName));
            if (curAnimations.size() == 0) {
                allAnimations.remove(entry.getKey());
            }
        }
    }

    /**
     * Add animation to the model
     * @param animation the animation to add in the model
     * @throws IllegalArgumentException if the animation cannot be added, such as the shape of the
     * animation does not exist, or the animation has a conflict with an existing animation
     */
    @Override
    public void addAnimation(AbstractAnimation animation) throws IllegalArgumentException{
        if (animation == null) {
            throw new IllegalArgumentException("Invalid animation to add");
        }
        if (!validAnimation(animation)) {
            throw new IllegalArgumentException("Invalid animation to add");
        }
        if (!allAnimations.containsKey(animation.getStartTime())) {
            allAnimations.put(animation.getStartTime(), new ArrayList<>());
        }
        allAnimations.get(animation.getStartTime()).add(animation);
    }

    /**
     * Return whether the animation to add is valid. It returns false if the animation cannot
     * be added, such as the shape of the animation does not exist, or the animation has a
     * conflict with an existing animation.
     * @param animation animation to add
     * @return whether the animation to add is valid
     */
    private boolean validAnimation(AbstractAnimation animation) {
        if (!allShapes.containsKey(animation.getShapeName())) {
            return false;
        }
        for (List<AbstractAnimation> animations : allAnimations.values()) {
            for (AbstractAnimation existingAnimation : animations) {
                if (existingAnimation.getShapeName().equals(animation.getShapeName())
                && existingAnimation.getAnimationType() == animation.getAnimationType()) {
                    if (!(existingAnimation.getStartTime() >= animation.getEndTime())
                        || (existingAnimation.getEndTime() <= animation.getStartTime())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
