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
        double curDimensionX = this.calculateState(fromDimension.get(0), toDimension.get(0), curTime);
        double curDimensionY = this.calculateState(fromDimension.get(1), toDimension.get(1), curTime);
        return shape.updateWithNewState(curDimensionX, curDimensionY);
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
