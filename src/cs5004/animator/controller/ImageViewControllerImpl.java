package cs5004.animator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.*;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.IShape;
import cs5004.animator.view.IView;
import cs5004.animator.view.IViewFeatures;

/**
 * A controller class that enables the program to output the description
 * of the shapes, draws the animation on a canvas.
 */
public class ImageViewControllerImpl implements IController, IViewFeatures {
  private final Timer timer;
  private int tick;
  private int tempo;
  private final IModel model;
  private final IView view;

  /**
   * Construct the controller.
   * @param model model
   * @param view view
   * @throws IllegalArgumentException if the given parameter is null
   */
  public ImageViewControllerImpl(IModel model, IView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Parameters can not be null");
    }
    this.model = model;
    this.view = view;
    this.tempo = view.getTempo();
    try {
      view.addListener(this);
    } catch (UnsupportedOperationException e) {
      // do nothing
    }

    this.timer = new Timer((int) ((1.0 / tempo) * 1000), ((e) -> {
      List<IReadOnlyShapes> shapesToRender = model.getUpdatedShapeAtGivenTime(tick);
      this.view.renderImage(shapesToRender);
      tick += 1;
    }));
    view.setCanvas(model.getCanvas());
  }

  /**
   * Run the animation.
   * @param ap appendable to append to
   * @throws IOException if the parameter is null
   */
  @Override
  public void run(Appendable ap) throws IOException {
    if (ap == null) {
      throw new IllegalStateException("Parameters can not be null");
    }
    List<IReadOnlyShapes> shapes = model.getReadOnlyShapes();
    Map<Integer, List<IAnimation>> animations = model.getAllAnimationSortedByTime();
    timer.start();
  }

  /**
   * Starts the animations.
   */
  @Override
  public void start() {
    if (tick == 0) {
      timer.start();
    }
//    if (tick > model.getLastTime()) {
//      tick = 0;
//    }
  }

  /**
   * Pauses the animations.
   */
  @Override
  public void paused() {
    timer.stop();
  }

  /**
   * Resumes the animations.
   */
  @Override
  public void resume() {
    if (tick > 0) {
      timer.start();
    }
  }

  /**
   * Restarts the animations.
   */
  @Override
  public void restart() {
    tick = 0;
    timer.start();
  }

  /**
   * Changes the speed of the animations to the given speed.
   *
   * @param value the given speed
   */
  @Override
  public void changeSpeed(int value) {
    this.timer.setDelay((int) ((1.0 / value * 1000)));
  }

//  /**
//   * Adds a shape to the animations.
//   *
//   * @param s the given shape
//   * @throws IllegalArgumentException the shape can't be added due to duplicate shapes.
//   */
//  @Override
//  public void addShape(IShape s) throws IllegalArgumentException {
//    model.addShape(s);
//  }
//
//  /**
//   * Removes the given shape.
//   *
//   * @param id the given shape's name
//   * @throws IllegalArgumentException if the given shape is not found.
//   */
//  @Override
//  public void removeShape(String id) throws IllegalArgumentException {
//    IShape temp = null;
//    for (IShape s : model.getAllShape()) {
//      if (s.getName().equals(id)) {
//        temp = s;
//      }
//    }
//    model.deleteShape(temp.getName());
//  }

//  /**
//   * Loops the animations.
//   *
//   * @param value indicates if the user wants to loop
//   */
//  @Override
//  public void loop(boolean value) {
//    loop = value;
//    if (value && model.getLastTime() < tick) {
//      timer.stop();
//      tick = 0;
//    }
//  }

//  /**
//   * Adds a key frame to the given shape.
//   *
//   * @param idShape the given shape's name
//   * @param work    the information of the keyframe
//   * @throws IllegalArgumentException if the given shape is not found or the information of the
//   *                                  keyframe is not valid
//   */
//  @Override
//  public void addKeyFrame(String idShape, int[] work) throws IllegalArgumentException {
//    Objects.requireNonNull(work);
//    model.addKeyFrameToShape(idShape, work);
//  }
//
//  /**
//   * Removes the keyframe from the given shape.
//   *
//   * @param id  the given shape's name
//   * @param key the current selected key
//   * @throws IllegalArgumentException if the given shape is not found or the information of the
//   *                                  keyframe is not valid
//   */
//  @Override
//  public void removeKeyFrame(String id, int key) throws IllegalArgumentException {
//    model.removeKeyFrameFromShape(id, key);
//  }
//
//  /**
//   * Edits the selected keyframe of a shape.
//   *
//   * @param id    the given shape's name
//   * @param key   the current selected key
//   * @param stuff the information of the keyframe
//   * @throws IllegalArgumentException if the given shape is not found or the information of the
//   *                                  keyframe is not valid
//   */
//  @Override
//  public void editKeyFrame(String id, int key, int[] stuff) throws IllegalArgumentException {
//    model.editKeyFrame(id, key, stuff);
//  }
//
//  /**
//   * Produces a list of shapes from the model.
//   *
//   * @return a list of shapes
//   */
//  public List<IReadOnlyShapes> getControllerShape() {
//    return model.convertShapes();
//  }
//
//  /**
//   * Produces a list of keyframes associated with the given shape.
//   *
//   * @param id given shape's name
//   * @return a list of keyframes associated with the shape
//   */
//  public List<String> getControllerMotion(String id) {
//    List<String> work = new ArrayList<>();
//    for (IReadOnlyShapes sh : model.convertShapes()) {
//      if (sh.getName().equals(id)) {
//        work.addAll(sh.getKeyFrame());
//      }
//    }
//    return work;
//  }
}
