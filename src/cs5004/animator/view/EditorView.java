package cs5004.animator.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;


import cs5004.animator.model.IAnimation;
import cs5004.animator.model.IReadOnlyShapes;
import cs5004.animator.model.Screen;

/**
 * A class that allows the user to edit the animations.
 */
public class EditorView extends AbstractView implements IView, ActionListener{
  private final ImageView delegate;

  private List<IViewFeatures> listeners;

  /**
   * A constructor that creates the edit panel.
   *
   * @param delegate the visual view that is rendered from the given model
   */
  public EditorView(ImageView delegate) {
    super(delegate.getTempo());

    listeners = new ArrayList<>();
    this.delegate = delegate;
    JMenuBar menuBar = new JMenuBar();

    delegate.setTitle("Editor");

    ImageIcon img = new ImageIcon("resources/pictures/doge-icon.jpg");

    ImageIcon pauseImg = new ImageIcon("resources/pictures/pause-icon.jpg");
    ImageIcon resumeImg = new ImageIcon("resources/pictures/resume-icon.jpg");
    ImageIcon restartImg = new ImageIcon("resources/pictures/restart-icon.jpg");

    JMenu control = new JMenu("Control");
    JMenuItem pause = new JMenuItem("Pause", pauseImg);
    JMenuItem resume = new JMenuItem("Resume", resumeImg);
    JMenuItem restart = new JMenuItem("Restart", restartImg);

    pause.setActionCommand("Pause");
    pause.addActionListener(this);
    resume.setActionCommand("Resume");
    resume.addActionListener(this);
    restart.setActionCommand("Restart");
    restart.addActionListener(this);


    control.add(pause);
    control.add(resume);
    control.add(restart);
    menuBar.add(control);

    delegate.setJMenuBar(menuBar);
    delegate.setIconImage(img.getImage());
  }

  /**
   * Adds all the IViewFeatures to the listeners to execute.
   *
   * @param vf given IViewFeatures
   * @throws UnsupportedOperationException if the given view doesn't support the listeners
   */
  @Override
  public void addListener(IViewFeatures vf) throws UnsupportedOperationException {
    this.listeners.add(vf);
  }


  @Override
  public String renderText(List<IReadOnlyShapes> allShapes, Map<Integer, List<IAnimation>> allAnimations) {
    return delegate.renderText(allShapes, allAnimations);
  }

  @Override
  public void renderImage(List<IReadOnlyShapes> allShapes) {
    delegate.renderImage(allShapes);
  }

  /**
   * setter that sets the default canvas to the given size.
   *
   * @param c given canvas size
   */
  @Override
  public void setCanvas(Screen c) {
    delegate.setCanvas(c);
  }

  /**
   * Gets the tempo that the user passes in.
   *
   * @return the tempo
   */
  @Override
  public int getTempo() {
    return delegate.getTempo();
  }

  /**
   * Checks if the given view has a listener field.
   *
   * @return true if it has a listener field
   */
  @Override
  public boolean hasListener() throws UnsupportedOperationException {
    return true;
  }

  /**
   * Action performed by the listener.
   *
   * @param e Action Event
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    for (IViewFeatures feat : listeners) {
      switch (e.getActionCommand()) {
        case "Resume":
          feat.resume();
          break;
        case "Pause":
          feat.paused();
          System.out.println("OK");
          break;
        case "Restart":
          feat.restart();
          break;
        default:
          tryAgain("How did you even get here?");
      }
    }
  }

  private void tryAgain(String s) {
    JFrame tryAgainWindow = new JFrame();
    JLabel text = new JLabel(s);
    text.setFont(new Font("Impact", Font.BOLD, 16));
    ImageIcon img = new ImageIcon("resources/gentleman-meme.jpg");
    UIManager.put("OptionPane.background", Color.WHITE);
    UIManager.put("Panel.background", Color.WHITE);

    JOptionPane.showMessageDialog(tryAgainWindow, text,
        "What? An Error?", JOptionPane.ERROR_MESSAGE, img);
  }
}