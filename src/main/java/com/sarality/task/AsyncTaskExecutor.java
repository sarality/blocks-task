package com.sarality.task;

import android.os.AsyncTask;

/**
 * Executes an Task in an background thread.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class AsyncTaskExecutor<I, P, R> extends AsyncTask<I, P, R> implements TaskProgressPublisher<P> {

  private final Task<I, P, R> task;

  private final TaskCompletionListener<R> completionListener;
  private final TaskProgressListener<P> progressListener;

  public AsyncTaskExecutor(Task<I, P, R> task, TaskProgressListener<P> progressListener,
      TaskCompletionListener<R> completionListener) {
    this.task = task;
    this.progressListener = progressListener;
    this.completionListener = completionListener;
  }

  public AsyncTaskExecutor(Task<I, P, R> task) {
    this(task, null, null);
  }

  @Override
  protected R doInBackground(I... inputs) {
    if (inputs == null) {
      return task.execute(null, this);
    } else {
      return task.execute(inputs[0], this);
    }
  }

  @Override
  protected void onPostExecute(R result) {
    super.onPostExecute(result);
    if (completionListener != null) {
      completionListener.onComplete(result);
    }
  }

  @Override
  protected void onProgressUpdate(P... progress) {
    super.onProgressUpdate(progress);
    if (progressListener != null) {
      if (progress != null) {
        progressListener.onProgressUpdate(progress[0]);
      } else {
        progressListener.onProgressUpdate(null);
      }
    }
  }

  @Override
  public void updateProgress(P progress) {
    publishProgress(progress);
  }
}
