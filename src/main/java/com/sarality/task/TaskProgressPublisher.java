package com.sarality.task;

/**
 * Publishes an update to the Progress Status of a Task
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface TaskProgressPublisher<P> {

  void updateProgress(P progress);
}
