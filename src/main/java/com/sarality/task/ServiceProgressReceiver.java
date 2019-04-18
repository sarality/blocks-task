package com.sarality.task;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * A Broadcast Receiver for progress broadcasts from a Service.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public abstract class ServiceProgressReceiver<T> extends BroadcastReceiver {

  private final String serviceStatusIntentParcelName;
  private final TaskProgressListener<T> progressListener;
  private final TaskCompletionListener<T> completionListener;


  public ServiceProgressReceiver(String serviceStatusIntentParcelName,
      TaskProgressListener<T> progressListener,
      TaskCompletionListener<T> completionListener) {
    super();
    this.serviceStatusIntentParcelName = serviceStatusIntentParcelName;
    this.progressListener = progressListener;
    this.completionListener = completionListener;
  }

  @Override
  public void onReceive(Context context, Intent intent) {

    T progressStatus = getProgressStatus(intent);
    if (isCompleted(progressStatus)) {
      if (completionListener != null) {
        completionListener.onComplete(progressStatus);
      }
    } else if (progressListener != null) {
      progressListener.onProgressUpdate(progressStatus);
    }
  }


  @SuppressWarnings("unchecked")
  private T getProgressStatus(Intent intent) {
    return (T) intent.getParcelableExtra(serviceStatusIntentParcelName);
  }

  protected abstract boolean isCompleted(T progressStatus);
}
