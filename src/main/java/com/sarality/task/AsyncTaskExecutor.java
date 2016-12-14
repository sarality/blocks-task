package com.sarality.task;

import android.os.AsyncTask;

import java.util.Arrays;

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
  protected R doInBackground(I... params) {
    if (params == null) {
      return task.execute(null, this);
    } else {
      return task.execute(Arrays.asList(params), this);
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
  protected void onProgressUpdate(P... values) {
    super.onProgressUpdate(values);
    if (progressListener != null) {
      if (values != null) {
        progressListener.onProgressUpdate(values[0]);
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
