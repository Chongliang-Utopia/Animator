package cs5004.animator;

import java.awt.desktop.SystemEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTextAreaUI;

import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.controller.IController;
import cs5004.animator.model.IModel;
import cs5004.animator.model.ModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.FactoryView;
import cs5004.animator.view.IView;

/**
 * A main class to run the program. Takes input from commandline, and initiate Model, Controller,
 * view accordingly, and then run the animation.
 * It throws an exception when the argument is invalid.
 */

public final class EasyAnimator {
  public static void main(String[] args) throws IOException {
    JFrame frame = new JFrame();
    boolean isFileName = false;
    boolean isSpeed = false;
    boolean isViewType = false;
    boolean isOutputName = false;
    String inputFile = "";
    String viewType = "";
    String outputName = "";
    int speed = 1;
    for (String arg : args) {
      if (arg.equals("-in")) {
        isFileName = true;
      } else if (arg.equals("-view")) {
        isViewType = true;
      } else if (arg.equals("-out")) {
        isOutputName = true;
      } else if (arg.equals("-speed")) {
        isSpeed = true;
      }
      // Get data.
      else if (isFileName) {
        inputFile = arg;
        isFileName = false;
      } else if (isViewType) {
        viewType = arg;
        isViewType = false;
      } else if (isSpeed) {
        try {
          speed = Integer.parseInt(arg);
        } catch (NumberFormatException e) {
          throw new NumberFormatException("Invalid speed, give an integer");
        }
        if (speed <= 0) {
          throw new IllegalArgumentException("speed need to be a positive number");
        }
        isSpeed = false;
      } else if (isOutputName) {
        outputName = arg;
        isSpeed = false;
      } else {
        JOptionPane.showMessageDialog(frame, "Could find valid input format",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    // Initiate.
    if (!inputFile.equals("") && !viewType.equals("")) {
      AnimationReader reader = new AnimationReader();
      IModel model = null;
      try {
        InputStream inputStream = new FileInputStream(inputFile);
        model = AnimationReader.parseFile(new InputStreamReader(inputStream), new ModelImpl.Builder());
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(frame,
            "FileNotFoundException: Could not find file",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
      IView view = FactoryView.makeView(viewType);
      IController controller = new ControllerImpl(model, view, speed);
      if (outputName.length() == 0) {
        controller.run(System.out);
      } else {
        try {
          FileWriter output = new FileWriter(outputName);
          controller.run(output);
          output.flush();
          output.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Could find valid input format",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}
