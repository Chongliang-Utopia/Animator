package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class modelImpl implements IModel {
    // Use shape name as the key for the map.
    private Map<String, AbstractShape> allShapes;
    // Use start time of the animation as the key for the map.
    private Map<Integer, AbstractAnimation> allAnimations;

    private modelImpl(Map<String, AbstractShape> allShapes,
                      Map<Integer, AbstractAnimation> allAnimations) {
        this.allShapes = allShapes;
        this.allAnimations = allAnimations;
    }

    @Override
    public Map<String, AbstractShape> getAllShape() {
        return new HashMap<>(allShapes);
    }

    @Override
    public Map<Integer, AbstractAnimation> getAllAnimation() {
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
        for (Map.Entry<Integer, AbstractAnimation> entry : allAnimations.entrySet()) {
            if (entry.getValue().getShapeName().equals(shapeName)) {
                allAnimations.remove(entry.getKey());
            }
        }
    }

    @Override
    public void addAnimation(AbstractAnimation animation) throws IllegalArgumentException{
        if (animation == null) {
            throw new IllegalArgumentException("Invalid animation to add");
        }

    }

    private boolean validAnimation(AbstractAnimation animation) {

    }
}
