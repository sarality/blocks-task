package com.sarality.task;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceProgressStatus implements Parcelable {

  private ServiceStatus serviceStatus;
  private int maxProgress;
  private int progress;

  public ServiceProgressStatus(ServiceStatus status, int progress, int maxProgress) {
    this.serviceStatus = status;
    this.progress = progress;
    this.maxProgress = maxProgress;
  }

  ServiceStatus getServiceStatus() {
    return serviceStatus;
  }

  public int getMaxProgress() {
    return maxProgress;
  }

  public int getProgress() {
    return progress;
  }

  public static final Creator CREATOR = new Creator() {
    @Override
    public ServiceProgressStatus createFromParcel(Parcel source) {
      return new ServiceProgressStatus(ServiceStatus.valueOf(source.readString()),
          source.readInt(),
          source.readInt());
    }

    @Override
    public ServiceProgressStatus[] newArray(int size) {
      return new ServiceProgressStatus[size];
    }
  };

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(serviceStatus.toString());
    dest.writeInt(progress);
    dest.writeInt(maxProgress);
  }
}
