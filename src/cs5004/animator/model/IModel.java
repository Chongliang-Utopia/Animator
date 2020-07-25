package cs5004.animator.model;

import java.util.List;
import java.util.Map;

public interface IModel {
    Map<String, AbstractShape> getAllShape();

    Map<Integer, AbstractAnimation> getAllAnimation();

    void addShape(AbstractShape shape);

    void deleteShape(String shapeName);

    void addAnimation(AbstractAnimation animation);
}
