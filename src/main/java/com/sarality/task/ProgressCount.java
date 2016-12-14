package com.sarality.task;

/**
 * A Progress that keeps track of maximum Progress and Current progress as integer values.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class ProgressCount {
  private int maxProgress;
  private int progress;

  public ProgressCount(int maxProgress, int progress) {
    this.maxProgress = maxProgress;
    this.progress = progress;
  }

  public int getMaxProgress() {
    return maxProgress;
  }

  public int getProgress() {
    return progress;
  }
}
