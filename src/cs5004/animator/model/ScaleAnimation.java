package cs5004.animator.model;

import java.util.List;

public class ScaleAnimation extends AbstractAnimation{
    // Width dimension.
    private List<Double> fromDimension;
    // Height dimension.
    private List<Double> toDimension;

    public ScaleAnimation(AnimationType animationType,
                          String shapeName,
                          int startTime,
                          int endTime,
                          List<Double> fromDimension,
                          List<Double> toDimension) throws IllegalArgumentException {
        super(animationType, shapeName, startTime, endTime);
        if (animationType != AnimationType.SCALE) {
            throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
        }
        if (fromDimension.size() != 2 || toDimension.size() != 2) {
            throw new IllegalArgumentException("Trying to initiate with wrong dimension scale.");
        }
        this.fromDimension = fromDimension;
        this.toDimension = toDimension;
    }


    @Override
    public AbstractShape runAnimation(AbstractShape shape, int curTime) {
        double curWidth = this.calculateState(fromDimension.get(0), toDimension.get(0), curTime);
        double curHeight = this.calculateState(fromDimension.get(1), toDimension.get(1), curTime);
        AbstractShape result = null;
        switch (shape.getType()) {
            case OVAL:
                result = new Oval(shape.getName(), shape.getType(), shape.getColor(),
                        shape.getPosition(),curWidth, curHeight,
                        shape.getAppearTime(), shape.getDisappearTime());
                break;
            case RECTANGLE:
                result = new Rectangle(shape.getName(), shape.getType(), shape.getColor(),
                        shape.getPosition(), curWidth, curHeight,
                        shape.getAppearTime(), shape.getDisappearTime());
                break;
            default: throw new IllegalArgumentException("Invalid animation to run");
        }
        return result;
    }

    @Override
    public String toString() {
        return "Shape " + this.getShapeName() + " scales from Width: " + fromDimension.get(0)
                + " Height: " + fromDimension.get(1)
                + " to Width: " + toDimension.get(0)
                + " Height: " + toDimension.get(1)
                + " from t=" + this.getStartTime() + " to t=" + this.getEndTime();
    }
}
