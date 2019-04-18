package com.sarality.task;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Generic Parcelable Progress for any Task running as a Service
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class TaskProgress implements Parcelable {

  private final String subTaskName;
  private final int maxProgress;
  private final int progress;

  public TaskProgress(String subTaskName, int progress, int maxProgress) {
    this.subTaskName = subTaskName;
    this.progress = progress;
    this.maxProgress = maxProgress;
  }

  public String getSubTaskName() {
    return subTaskName;
  }

  public int getMaxProgress() {
    return maxProgress;
  }

  public int getProgress() {
    return progress;
  }

  public static final Creator CREATOR = new Creator() {
    @Override
    public TaskProgress createFromParcel(Parcel source) {
      return new TaskProgress(
          source.readString(),
          source.readInt(),
          source.readInt());
    }

    @Override
    public TaskProgress[] newArray(int size) {
      return new TaskProgress[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(subTaskName);
    dest.writeInt(progress);
    dest.writeInt(maxProgress);
  }
}
