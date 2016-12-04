package com.sarality.task;

/**
 * Interface for classes that need to be notified on completion of an Async Task
 *
 * @author Satya@ (Satya Puniani)
 */
public interface TaskCompletionListener<R> {

  void onComplete(R result);
}
