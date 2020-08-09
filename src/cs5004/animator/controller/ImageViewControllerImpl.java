package cs5004.animator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IReadOnlyShapes;
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

}
