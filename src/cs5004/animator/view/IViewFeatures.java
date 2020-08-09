package cs5004.animator.view;

/**
 * The interface IViewFeatures gives functionality that the editorView can use including run, pause,
 * resume, restart, and edit shapes etc.
 */
public interface IViewFeatures {

  /**
   * Pauses the animations.
   */
  void paused();

  /**
   * Resumes the animations.
   */
  void resume();

  /**
   * Restarts the animations.
   */
  void restart();
}