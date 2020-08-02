package cs5004.animator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.IModel;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.view.IView;

public class ControllerImpl implements IController {
  private final Timer timer;
  private int tick;
  private final IModel model;
  private final IView view;

  public ControllerImpl(IModel model, IView view, int tempo) throws IllegalArgumentException{
    if (model == null || view == null) {
      throw new IllegalArgumentException("Parameters can not be null");
    }
    this.model = model;
    this.view = view;
    this.timer = new Timer((int) ((1.0 / tempo) * 1000), ((e) -> {
      Map<String, IReadOnlyShapes> shapesToRender = model.getUpdatedShapeAtGivenTime(tick++);
      view.renderImage(shapesToRender);
      tick = 0;
    }));
    view.setCanvas(model.getCanvas());
  }

  @Override
  public void run(Appendable ap) throws IOException {
    if (ap == null) {
      throw new IllegalStateException("Parameters can not be null");
    }
    Map<String, IReadOnlyShapes> shapes = model.getReadOnlyShapes();
    Map<Integer, List<AbstractAnimation>> animations = model.getAllAnimationSortedByTime();
    String text = view.renderText(shapes, animations);
    try {
      // write to file/stdin
      ap.append(text);
    } catch (IOException e) {
      throw new IOException("Cannot append");
    }
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
}
