package com.sarality.task;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Generic Parcelable Progress for any Task running as a Service with details on number of
 * tasks successfully completed, number of failures, and number skipped.
 *
 * @author abhideep@ (Abhideep Singh)
 */
public class DetailedTaskProgress extends TaskProgress implements Parcelable {

  private final int numSuccess;
  private final int numFailure;
  private final int numSkipped;

  public DetailedTaskProgress(String subTaskName, int progress, int maxProgress, int numSuccess, int numFailure,
      int numSkipped) {
    super(subTaskName, progress, maxProgress);
    this.numSuccess = numSuccess;
    this.numFailure = numFailure;
    this.numSkipped = numSkipped;
  }

  public int getNumSuccess() {
    return numSuccess;
  }

  public int getNumFailure() {
    return numFailure;
  }

  public int getNumSkipped() {
    return numSkipped;
  }

  public static final Creator CREATOR = new Creator() {
    @Override
    public DetailedTaskProgress createFromParcel(Parcel source) {
      return new DetailedTaskProgress(
          source.readString(),
          source.readInt(),
          source.readInt(),
          source.readInt(),
          source.readInt(),
          source.readInt());
    }

    @Override
    public DetailedTaskProgress[] newArray(int size) {
      return new DetailedTaskProgress[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    super.writeToParcel(dest, flags);
    dest.writeInt(numSuccess);
    dest.writeInt(numFailure);
    dest.writeInt(numSkipped);
  }
}
