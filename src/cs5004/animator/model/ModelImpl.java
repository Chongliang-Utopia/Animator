package cs5004.animator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import cs5004.animator.util.AnimationBuilder;

/**
 * A class represent the implementation of the IModel, a concrete class as the model of the
 * animator. Internally, it stores all the shapes in a list. It has stores all the animations
 * in a Map, using the start time of
 * the animation as the key since the order of the animation matters. Externally, it allows to get a
 * deep copy of all the shapes and a deep copy of the animations. It also
 * include methods to add a shape, delete a shape and add an animation. Additionally, it offers a
 * method to get a copy of all the invisible shapes at a given time.
 * Changes: add the builder inner class to build the Model.
 */
public class ModelImpl implements IModel {
  // Use start time of the animation as the key for the map.
  // It stores a list of animation under the specific time since the start time matters.
  private Map<Integer, List<IAnimation>> allAnimations;
  // Store a list of all the shapes.
  private List<IShape> allShapes;
  private Screen canvas;

  /**
   * Construct a modelImpl class with the shapes and animations.
   */
  public ModelImpl() {
    this.allAnimations = new TreeMap<>(Comparator.comparingInt(a -> a));
    this.canvas = new Screen(0, 0, 500, 500);
    this.allShapes = new ArrayList<>();
  }

  /**
   * Construct a modelImpl class with the shapes and animations.
   *
   * @param allShapes     shapes to include in the model
   * @param allAnimations animations to include in the model
   * @throws IllegalArgumentException if the parameter is null
   */
  public ModelImpl(List<IShape> allShapes,
                   Map<Integer, List<IAnimation>> allAnimations, Screen canvas) throws
      IllegalArgumentException {
    if (allAnimations == null || allShapes == null) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.allShapes = allShapes;
    this.allAnimations = allAnimations;
    this.canvas = new Screen(0, 0, 1000, 1000);
  }

  /**
   * Constructor one ModelImpl class and fields provided by builder.
   *
   * @param builder thee given builder of the ModelImpl class
   */
  private ModelImpl(Builder builder) {
    this.allShapes = builder.model.getAllShape();
    this.allAnimations = builder.model.getAllAnimationSortedByTime();
    this.canvas = builder.model.getCanvas();
  }


  /**
   * A builder class that enables the program to read the existing files and convert the information
   * to an Animation model.
   */
  public static final class Builder implements AnimationBuilder<IModel> {
    private IModel model;
    private Map<String, ShapeType> nameToType = new HashMap<>();

    /**
     * Initiate the builder.
     */
    public Builder() {
      this.model = new ModelImpl();
    }

    /**
     * Build the model.
     * @return IModel built
     */
    @Override
    public IModel build() {
      return new ModelImpl(this);
    }

    /**
     * Set bounds for the animation.
     * @param x The leftmost x value
     * @param y The topmost y value
     * @param width The width of the bounding box
     * @param height The height of the bounding box
     * @return An AnimationBuilder
     */
    @Override
    public AnimationBuilder<IModel> setBounds(int x, int y, int width, int height) {
      this.model.setCanvas(new Screen(x, y, width, height));
      return this;
    }

    /**
     * Adds a new shape to the growing document.
     * @param name The unique name of the shape to be added.
     *             No shape with this name should already exist.
     * @param type The type of shape (e.g. "ellipse", "rectangle") to be added.
     *             The set of supported shapes is unspecified, but should
     *             include "ellipse" and "rectangle" as a minimum.
     * @return this builder
     */
    @Override
    public AnimationBuilder<IModel> declareShape(String name, String type) {
      switch (type) {
        case "rectangle":
          this.model.addShape(ShapeFactory.buildShape(name, ShapeType.RECTANGLE));
          nameToType.put(name, ShapeType.RECTANGLE);
          break;
        case "ellipse":
          this.model.addShape(ShapeFactory.buildShape(name, ShapeType.OVAL));
          nameToType.put(name, ShapeType.OVAL);
          break;
        default:
          break;
      }
      return this;
    }

    /**
     * Adds a transformation to the growing document.
     * @param name The name of the shape (added with {@link AnimationBuilder#declareShape})
     * @param t1   The start time of this transformation
     * @param x1   The initial x-position of the shape
     * @param y1   The initial y-position of the shape
     * @param w1   The initial width of the shape
     * @param h1   The initial height of the shape
     * @param r1   The initial red color-value of the shape
     * @param g1   The initial green color-value of the shape
     * @param b1   The initial blue color-value of the shape
     * @param t2   The end time of this transformation
     * @param x2   The final x-position of the shape
     * @param y2   The final y-position of the shape
     * @param w2   The final width of the shape
     * @param h2   The final height of the shape
     * @param r2   The final red color-value of the shape
     * @param g2   The final green color-value of the shape
     * @param b2   The final blue color-value of the shape
     * @throws IllegalArgumentException if the name of the shape does not exist
     * @return This {@link AnimationBuilder}
     */
    @Override
    public AnimationBuilder<IModel> addMotion(String name, int t1, int x1, int y1,
                                              int w1, int h1, int r1, int g1, int b1,
                                              int t2, int x2, int y2,
                                              int w2, int h2, int r2, int g2, int b2)
        throws IllegalArgumentException {
      if (!nameToType.containsKey(name)) {
        throw new IllegalArgumentException("This shape does not exist");
      }
      this.model.addAnimation(new AnimationOperation(name, nameToType.get(name),
          t1, x1, y1, w1, h1, r1, g1, b1,
          t2, x2, y2, w2, h2, r2, g2, b2));
      return this;
    }
  }


  /**
   * Return a copy of the shapes in the model.
   *
   * @return a copy of the shapes in the model, use shape name as the key for the map.
   */
  @Override
  public List<IShape> getAllShape() {
    return new ArrayList<>(allShapes);
  }


  /**
   * Return a copy of all the animations in the model sorted by time.
   *
   * @return a copy of all the animations in the model sorted by time, use start time of the
   *         animation as the key for the map, it stores a list of animation under the specific time
   */
  @Override
  public Map<Integer, List<IAnimation>> getAllAnimationSortedByTime() {
    Map<Integer, List<IAnimation>> sortedAnimations = new TreeMap<>(
        Comparator.comparingInt(a -> a));
    for (int key : allAnimations.keySet()) {
      sortedAnimations.put(key, new ArrayList<>(allAnimations.get(key)));
    }
    return sortedAnimations;
  }

  /**
   * Return a copy of the ReadOnlyShapes in the model.
   *
   * @return a copy of the ReadOnlyShapes in the model, use shape name as the key for the map.
   */
  @Override
  public List<IReadOnlyShapes> getReadOnlyShapes() {
    List<IReadOnlyShapes> ret = new ArrayList<>(allShapes);
    return ret;
  }

  /**
   * Return a list of all the updated shape at given time.
   * @param time the time to check the updated shapes
   * @return a list of all the updated shape at given time
   */
  @Override
  public List<IReadOnlyShapes> getUpdatedShapeAtGivenTime(int time) {
    List<IReadOnlyShapes> ret = new ArrayList<>();
    Map<String, Integer> shapeNameToIndex = new HashMap<>();
    int curIndex = 0;
    for (IShape sh : allShapes) {
      if (sh.getDisappearTime() >= time && sh.getAppearTime() <= time) {
        ret.add(sh);
        shapeNameToIndex.put(sh.getName(), curIndex);
        curIndex++;
      }
    }
    for (int key : allAnimations.keySet()) {
      // Check if the animation has started.
      if (key > time) {
        break;
      }
      for (IAnimation ani : allAnimations.get(key)) {
        if (ani.getEndTime() < time) {
          continue;
        }
        IShape shape = ani.runAnimation(time);
        if (shapeNameToIndex.containsKey(shape.getName())) {
          ret.set(shapeNameToIndex.get(shape.getName()), shape);
        }
      }
    }
    return ret;
  }

  /**
   * Add a shape to the model.
   *
   * @param shape the shape to add in the model
   * @throws IllegalArgumentException if the shape cannot be added, such as the shape has an
   *                                  identical name with an existing shape in the model
   */
  @Override
  public void addShape(IShape shape) throws IllegalArgumentException {
    if (findShape(shape.getName()) != null) {
      throw new IllegalArgumentException(
          "Cannot create add a shape with an existing shape name");
    }
    allShapes.add(shape);
  }

  /**
   * Helper method to return the shape with the given name. Null if not found
   * @param shapeName name of the shape to add
   * @return the shape with the given name, null if not found
   */
  private IShape findShape(String shapeName) {
    for (IShape s : allShapes) {
      if (s.getName().equals(shapeName)) {
        return s;
      }
    }
    return null;
  }

  /**
   * Delete a shape in the model.
   *
   * @param shapeName the name of the shape to delete
   * @throws IllegalArgumentException if the delete operation cannot be successful when the shape
   *                                  does not exist in the model
   */
  @Override
  public void deleteShape(String shapeName) throws IllegalArgumentException {
    if (findShape(shapeName) == null) {
      throw new IllegalArgumentException("Shape not found.");
    }
    // Remove shape.
    allShapes.remove(findShape(shapeName));
    // Remove corresponding animations with this removed shape.
    Map<Integer, List<IAnimation>> updatedAnimations = new HashMap<>();
    for (Map.Entry<Integer, List<IAnimation>> entry : allAnimations.entrySet()) {
      List<IAnimation> curAnimations = entry.getValue();
      List<IAnimation> updatedAnimationList = new ArrayList<>();
      for (IAnimation ani : curAnimations) {
        if (!ani.getShapeName().equals(shapeName)) {
          updatedAnimationList.add(ani);
        }
      }
      updatedAnimations.put(entry.getKey(), updatedAnimationList);
    }
    this.allAnimations = updatedAnimations;
  }

  /**
   * Add animation to the model.
   *
   * @param animation the animation to add in the model
   * @throws IllegalArgumentException if the animation cannot be added, such as the shape of the
   *                                  animation does not exist, or the animation has a conflict with
   *                                  an existing animation
   */
  @Override
  public void addAnimation(IAnimation animation) throws IllegalArgumentException {
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
    IShape shape = findShape(animation.getShapeName());
    assert shape != null;
    shape.setAppearTime(Math.min(shape.getAppearTime(), animation.getStartTime()));
    shape.setDisappearTime(Math.max(shape.getDisappearTime(), animation.getEndTime()));
    if (animation.getStartTime() == shape.getAppearTime()) {
      allShapes.set(allShapes.indexOf(shape), animation.runAnimation(animation.getStartTime()));
    }
  }

  /**
   * Return the canvas.
   * @return the canvas
   */
  @Override
  public Screen getCanvas() {
    return new Screen(this.canvas.getLocX(), this.canvas.getLocY(),
        this.canvas.getCanvasW(), this.canvas.getCanvasH());
  }

  /**
   * Set canvas.
   * @param canvas canvas to set
   */
  @Override
  public void setCanvas(Screen canvas) {
    this.canvas = canvas;
  }

  /**
   * Return whether the animation to add is valid. It returns false if the animation cannot be
   * added, such as the shape of the animation does not exist, or the animation has a conflict with
   * an existing animation.
   *
   * @param animation animation to add
   * @return whether the animation to add is valid
   */
  private boolean validAnimation(IAnimation animation) {
    // This shape has to exist in our allShapes map.
    if (findShape(animation.getShapeName()) == null) {
      return false;
    }
    for (List<IAnimation> animations : allAnimations.values()) {
      for (IAnimation existingAnimation : animations) {
        // Check for contradiction.
        if (existingAnimation.getShapeName().equals(animation.getShapeName())) {
          boolean notValid = (existingAnimation.getStartTime() < animation.getEndTime())
              && (existingAnimation.getEndTime() > animation.getStartTime());
          if (notValid) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * Return a String representation of all the shapes and animations.
   *
   * @return a String representation of all the shapes and animations
   */
  public String toString() {
    StringBuilder ret = new StringBuilder("Shapes:\n");
    for (IShape shape : allShapes) {
      ret.append(shape.textRender());
      ret.append("\n");
    }
    for (List<IAnimation> aniLst : allAnimations.values()) {
      for (IAnimation ani : aniLst) {
        ret.append(ani.toString());
      }
    }
    return ret.toString();
  }

//
//  /**
//   * adds a keyframe to the given shape.
//   *
//   * @param name  the name of the shape
//   * @param stuff the information of the keyframe
//   * @throws IllegalArgumentException if no shape is found
//   */
//  @Override
//  public void addKeyFrameToShape(String name, int[] stuff) throws IllegalArgumentException {
//    Objects.requireNonNull(name);
//    try {
//      new Size(stuff[3], stuff[4]);
//      new RGB(stuff[5], stuff[6], stuff[7]);
//    } catch (IllegalArgumentException e) {
//      throw new IllegalArgumentException(e);
//    }
//    double[] start = new double[]{stuff[0], stuff[1],
//        stuff[2], stuff[3], stuff[4], stuff[5], stuff[6], stuff[7]};
//    for (Shapes sh : shapes) {
//      if (sh.getName().equals(name)) {
//        if (!sh.getFrames().containsKey(stuff[0])) {
//          sh.getFrames().put(stuff[0], start);
//        } else {
//          throw new IllegalArgumentException("the key frame can't be added.");
//        }
//      }
//    }
//  }
//
//  /**
//   * Removes a keyframe from the given shape.
//   *
//   * @param name  the given shape's name
//   * @param key the keyframe that should be removed
//   * @throws IllegalArgumentException if no shape or keyframe is found
//   */
//  @Override
//  public void removeKeyFrameFromShape(String name, int key) throws IllegalArgumentException {
//    Objects.requireNonNull(name);
//    for (IShape sh : shapes) {
//      if (sh.getName().equals(name)) {
//        sh.getFrames().remove(key);
//      }
//    }
//  }
//
//  /**
//   * Edits the given keyframe.
//   *
//   * @param id    the name of the shape
//   * @param key   the given keyframe
//   * @param stuff the information of the keyframe
//   * @throws IllegalArgumentException if the shape of keyframe is found
//   */
//  @Override
//  public void editKeyFrame(String id, int key, int[] stuff) throws IllegalArgumentException {
//    Objects.requireNonNull(id);
//    try {
//      new Size(stuff[3], stuff[4]);
//      new ColorType(stuff[5], stuff[6], stuff[7]);
//    } catch (IllegalArgumentException e) {
//      throw new IllegalArgumentException(e);
//    }
//    for (IShape sh : allShapes) {
//      if (sh.getName().equals(id)) {
//        sh.getFrames().remove(key);
//        sh.getFrames().put(stuff[0], new double[]{stuff[0], stuff[1],
//            stuff[2], stuff[3], stuff[4], stuff[5], stuff[6], stuff[7]});
//      }
//    }
//  }

}
