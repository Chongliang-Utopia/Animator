package cs5004.animator.model;


public abstract class AbstractAnimation {
    protected AnimationType animationType;
    protected String shapeName;
    protected int startTime;
    protected int endTime;

    public AbstractAnimation(AnimationType animationType, String shapeName, int startTime, int endTime) throws IllegalArgumentException{
        if (startTime < 0 || endTime < 0 || startTime >= endTime) {
            throw new IllegalArgumentException("Invalid time frame for the animation.");
        }
        this.animationType = animationType;
        this.shapeName = shapeName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getShapeName() {
        return this.shapeName;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public int getEndTime() {
        return this.endTime;
    }

    public abstract AbstractShape runAnimation(AbstractShape shape, int curTime);

    protected float calculateState(float startState, float endState, int curTime){
        if (curTime <= startTime) {
            return startState;
        }
        else if (curTime >= endTime) {
            return endState;
        }
        return startState + (endState - startState) / (endTime - startTime) * (curTime - startTime);
    }

}

