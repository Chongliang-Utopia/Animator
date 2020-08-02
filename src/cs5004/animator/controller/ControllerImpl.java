package cs5004.animator.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

import cs5004.animator.model.AbstractAnimation;
import cs5004.animator.model.AbstractShape;
import cs5004.animator.model.IModel;

public class ControllerImpl implements IController {
  private final Timer timer;
  private int tick;
  private final IModel model;
  private final IView view;

  public ControllerImpl(IModel model, IView view) throws IllegalArgumentException{
    if (model == null || view == null) {
      throw new IllegalArgumentException("Parameters can not be null");
    }
    this.model = model;
    this.view = view;
    this.timer = new Timer((int) ((1.0 / view.getTempo()) * 1000), ((e) -> {
      List<AbstractShape> shapesToRender = model.getUpdatedShapeAtGivenTime(tick++);
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
    Map<String, AbstractShape> shapes = model.getAllShape();
    Map<Integer, List<AbstractAnimation>> animations = model.getAllAnimationSortedByTime();
    String text = view.renderText(shapes, animations);
    try {
      ap.append(text);
    } catch (IOException e) {
      throw new IOException("Cannot append");
    }
    timer.start();
  }
}