
package com.ibm.mobileappbuilder.earthquakes20160930142129.ds;

import ibmmobileappbuilder.mvp.model.MutableIdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class EarthquakesDSSchemaItem implements Parcelable, MutableIdentifiableBean {

    private transient String cloudantIdentifiableId;


    @Override
    public void setIdentifiableId(String id) {
        this.cloudantIdentifiableId = id;
    }

    @Override
    public String getIdentifiableId() {
        return cloudantIdentifiableId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cloudantIdentifiableId);
    }

    public static final Creator<EarthquakesDSSchemaItem> CREATOR = new Creator<EarthquakesDSSchemaItem>() {
        @Override
        public EarthquakesDSSchemaItem createFromParcel(Parcel in) {
            EarthquakesDSSchemaItem item = new EarthquakesDSSchemaItem();
            item.cloudantIdentifiableId = in.readString();

            return item;
        }

        @Override
        public EarthquakesDSSchemaItem[] newArray(int size) {
            return new EarthquakesDSSchemaItem[size];
        }
    };
}


