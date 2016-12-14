package com.sarality.task;

/**
 * Interface for classes that need to be notified on the progress of a Async task
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface TaskProgressListener<P> {

  void onProgressUpdate(P progress);
}
