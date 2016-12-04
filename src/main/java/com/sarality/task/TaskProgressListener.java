package com.sarality.task;

/**
 * Interface for classes that need to be notified on the progress of a Async task
 *
 * @author Satya@ (Satya Puniani)
 */
public interface TaskProgressListener {

  void onProgress(int maxProgress, int progress);

}
