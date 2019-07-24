package com.sarality.task;

/**
 * Publishes a Detailed Task Progress on the completion of a SubTask
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class DetailedTaskProgressPublisher<I> implements TaskCompletionListener<TaskCompletionStatus<I>> {
  private final String taskName;
  private final int maxProgress;
  private int progress;
  private int success;
  private int skipped;
  private int failure;

  public DetailedTaskProgressPublisher(String taskName, int maxProgress) {
    this.taskName = taskName;
    this.maxProgress = maxProgress;
  }

  @Override
  public void onComplete(TaskCompletionStatus<I> result) {
    I taskId = result.getTaskIdentifier();
    TaskCompletionType completionType = result.getCompletionType();

    synchronized (this) {
      if (!hasTaskBeenProcessed(taskId)) {
        progress++;
        if (completionType == TaskCompletionType.SUCCESS) {
          success++;
        } else if (completionType == TaskCompletionType.SKIPPED){
          skipped++;
        } else if (completionType == TaskCompletionType.FAILED) {
          failure++;
        }
        markTaskAsProcessed(taskId);
      }
    }
    publishProgress(generateProgress(taskName));
  }

  protected abstract boolean hasTaskBeenProcessed(I taskId);

  protected abstract void markTaskAsProcessed(I taskId);

  protected abstract void publishProgress(DetailedTaskProgress progress);

  public DetailedTaskProgress generateProgress(String progressTaskName) {
    return new DetailedTaskProgress(progressTaskName, progress, maxProgress, success, failure, skipped);
  }
}
