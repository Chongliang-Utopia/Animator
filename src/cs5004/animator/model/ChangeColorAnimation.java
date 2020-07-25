package cs5004.animator.model;

public class ChangeColorAnimation extends AbstractAnimation {
    ColorInfo startColor;
    ColorInfo endColor;

    public ChangeColorAnimation(AnimationType animationType,
                                String shapeName,
                                int startTime,
                                int endTime,
                                ColorInfo startColor,
                                ColorInfo endColor) throws IllegalArgumentException {
        super(animationType, shapeName, startTime, endTime);
        if (animationType != AnimationType.CHANGECOLOR) {
            throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
        }
        this.startColor = startColor;
        this.endColor = endColor;
    }


    @Override
    public AbstractShape runAnimation(AbstractShape shape, int curTime) {
        ColorInfo curColor = new ColorInfo(this.calculateState(startColor.getRed(), endColor.getRed(), curTime),
                                            this.calculateState(startColor.getGreen(), endColor.getGreen(), curTime),
                                            this.calculateState(startColor.getBlue(), endColor.getBlue(), curTime));
        return shape.updateWithNewState(curColor);
    }

    @Override
    public String toString() {
        return "Shape " + this.getShapeName() + " changes color from " + startColor.toString()
                + " to " + endColor.toString()
                + " from t=" + this.getStartTime() + " to t=" + this.getEndTime();
    }
}
