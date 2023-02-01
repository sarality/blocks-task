package com.sarality.task;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ServiceProgressNotifier {
  private final int notificationId;
  private final String channelName;
  private NotificationCompat.Builder builder;
  private NotificationManagerCompat notificationManager;
  private final int contentTitle;
  private final int contentText;
  private final int drawableResourceId;
  private final String serviceCompletedText;
  private final String serviceRunningText;

  public ServiceProgressNotifier(int notificationId, String channelName,
                          int contentTitle, int contentText, int drawableResourceId,
                          String serviceCompletedText, String serviceRunningText) {
    this.notificationId = notificationId;
    this.channelName = channelName;
    this.contentTitle = contentTitle;
    this.contentText = contentText;
    this.drawableResourceId = drawableResourceId;
    this.serviceCompletedText = serviceCompletedText;
    this.serviceRunningText = serviceRunningText;
  }

  public Notification init(IntentService service, Class<? extends Activity> intentActivityClass) {
    createNotificationChannel(service);

    // Create an explicit intent to launch an Activity on tap of the notification
    PendingIntent pendingIntent = null;
    if (intentActivityClass != null) {
      Intent notificationIntent = new Intent(service, intentActivityClass);
      notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      pendingIntent = PendingIntent.getActivity(service, 0, notificationIntent, 0);
    }
    notificationManager = NotificationManagerCompat.from(service);
    builder = new NotificationCompat.Builder(service, channelName);
    builder
        .setContentTitle(service.getString(contentTitle))
        .setContentText(service.getString(contentText))
        .setSmallIcon(drawableResourceId)
        .setPriority(NotificationCompat.PRIORITY_LOW)
        ;
    if (pendingIntent != null) {
        builder.setContentIntent(pendingIntent);
    }
    return builder.build();
  }

  public void notify(ServiceProgressStatus progress) {
    int maxProgress = progress.getMaxProgress();
    int currentProgress = progress.getProgress();
    if (progress.getServiceStatus() != null && progress.getServiceStatus().equals(ServiceStatus.FINISHED)) {
      builder.setContentText(serviceCompletedText);
      builder.setProgress(maxProgress, currentProgress, true);
      notificationManager.notify(notificationId, builder.build());
      cancel();
    } else {
      builder.setContentText(serviceRunningText);
      builder.setProgress(maxProgress, currentProgress, true);
      notificationManager.notify(notificationId, builder.build());
    }
  }

  void cancel() {
    notificationManager.cancel(notificationId);
  }

  /**
   * for API26+, create a notification channel
   *
   * @param context : context of service which has to be notified
   */
  private void createNotificationChannel(Context context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      int importance = NotificationManager.IMPORTANCE_LOW;
      NotificationChannel channel = new NotificationChannel(channelName, channelName, importance);
      channel.setDescription(channelName);
      NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
      if (notificationManager != null) {
        notificationManager.createNotificationChannel(channel);
      }
    }
  }
}
