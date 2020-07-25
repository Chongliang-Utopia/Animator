package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class modelImpl implements IModel {
    // Use shape name as the key for the map.
    private Map<String, AbstractShape> allShapes;
    // Use start time of the animation as the key for the map.
    // It stores a list of animation under the specific time.
    private Map<Integer, List<AbstractAnimation>> allAnimations;

    private modelImpl(Map<String, AbstractShape> allShapes,
                      Map<Integer, List<AbstractAnimation>> allAnimations) {
        this.allShapes = allShapes;
        this.allAnimations = allAnimations;
    }

    @Override
    public Map<String, AbstractShape> getAllShape() {
        return new HashMap<>(allShapes);
    }

    @Override
    public Map<Integer, List<AbstractAnimation>> getAllAnimation() {
        return new HashMap<>(allAnimations);
    }

    @Override
    public void addShape(AbstractShape shape) throws IllegalArgumentException{
        if (allShapes.containsKey(shape.getName())) {
            throw new IllegalArgumentException(
                    "Cannot create add a shape with an existing shape name");
        }
        allShapes.put(shape.getName(), shape);
    }

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
