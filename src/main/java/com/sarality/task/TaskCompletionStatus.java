package com.sarality.task;

/**
 * The status that is published when a single Sub Task is Completed.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TaskCompletionStatus<I> {
  private final I taskIdentifier;
  private TaskCompletionType completionType;

  public TaskCompletionStatus(I taskIdentifier, TaskCompletionType completionType) {
    this.taskIdentifier = taskIdentifier;
    this.completionType = completionType;
  }

  I getTaskIdentifier() {
    return taskIdentifier;
  }

  TaskCompletionType getCompletionType() {
    return completionType;
  }
}
