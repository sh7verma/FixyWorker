package com.app.fixy.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by app on 13-Dec-17.
 */

public class LocationModel implements Parcelable {

    String ID,latitude,longitude,labelName,place;
    String house_flat,landmark;
    int type;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getHouse_flat() {
        return house_flat;
    }

    public void setHouse_flat(String house_flat) {
        this.house_flat = house_flat;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
        dest.writeString(this.labelName);
        dest.writeInt(this.type);
        dest.writeString(this.place);
        dest.writeString(this.house_flat);
        dest.writeString(this.landmark);
    }

    public LocationModel() {
    }

    protected LocationModel(Parcel in) {
        this.ID = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
        this.labelName = in.readString();
        this.type = in.readInt();
        this.place = in.readString();
        this.house_flat = in.readString();
        this.landmark = in.readString();
    }

    public static final Parcelable.Creator<LocationModel> CREATOR = new Parcelable.Creator<LocationModel>() {
        @Override
        public LocationModel createFromParcel(Parcel source) {
            return new LocationModel(source);
        }

        @Override
        public LocationModel[] newArray(int size) {
            return new LocationModel[size];
        }
    };
}
