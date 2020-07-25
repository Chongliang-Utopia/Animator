package cs5004.animator.model;

/**
 * A class represents an animation to change the color, it extends the AbstractAnimation
 * class. It stores the start color and end color of the animation.
 * It can also call a method to run the animation and get an updated Abstract shape.
 */
public class ChangeColorAnimation extends AbstractAnimation {
    ColorType startColor;
    ColorType endColor;

    /**
     * Construct an ChangeColorAnimation object.
     * @param animationType the type of the animation
     * @param shapeName the name of the shape
     * @param startTime the start time of the animation
     * @param endTime the end time of the animation
     * @param startColor the start color of the animation
     * @param endColor the end color of the animation
     * @throws IllegalArgumentException
     */
    public ChangeColorAnimation(AnimationType animationType,
                                String shapeName,
                                int startTime,
                                int endTime,
                                ColorType startColor,
                                ColorType endColor) throws IllegalArgumentException {
        super(animationType, shapeName, startTime, endTime);
        if (animationType != AnimationType.CHANGECOLOR) {
            throw new IllegalArgumentException("Trying to initiate with wrong animation type.");
        }
        this.startColor = startColor;
        this.endColor = endColor;
    }


    /**
     * Abstract method to run the animation, which returns an updated abstract shape.
     * @param shape shape to run the animation
     * @param curTime current time
     * @return an updated abstract shape
     * @throws IllegalArgumentException if an updated shape cannot be generated
     */
    @Override
    public AbstractShape runAnimation(AbstractShape shape, int curTime)
            throws IllegalArgumentException {
        ColorType curColor = new ColorType(
                (float)this.calculateState(startColor.getRed(), endColor.getRed(), curTime),
                (float)this.calculateState(startColor.getGreen(), endColor.getGreen(), curTime),
                (float)this.calculateState(startColor.getBlue(), endColor.getBlue(), curTime));
        AbstractShape result = ShapeFactory.buildShape(
                shape.getType(), shape.getName(), shape.getType(), curColor,
                shape.getPosition(), shape.getWidth(), shape.getHeight(),
                shape.getAppearTime(), shape.getDisappearTime());
        if (result == null) {
            throw new IllegalArgumentException("Invalid animation to run on this shape");
        }
        return result;
    }

    /**
     * Return a String representation of the animation.
     * @return a String representation of the animation
     */
    @Override
    public String toString() {
        return "Shape " + this.getShapeName() + " changes color from " + startColor.toString()
                + " to " + endColor.toString()
                + " from t=" + this.getStartTime() + " to t=" + this.getEndTime();
    }
}
