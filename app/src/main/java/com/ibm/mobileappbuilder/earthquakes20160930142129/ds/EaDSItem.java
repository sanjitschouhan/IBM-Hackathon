
package com.ibm.mobileappbuilder.earthquakes20160930142129.ds;
import ibmmobileappbuilder.ds.restds.GeoPoint;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class EaDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("magnitude") public Double magnitude;
    @SerializedName("place") public String place;
    @SerializedName("url") public String url;
    @SerializedName("time") public Long time;
    @SerializedName("long") public GeoPoint long_;
    @SerializedName("id") public String id;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(magnitude);
        dest.writeString(place);
        dest.writeString(url);
        dest.writeValue(time);
        dest.writeDoubleArray(long_ != null  && long_.coordinates.length != 0 ? long_.coordinates : null);
        dest.writeString(id);
    }

    public static final Creator<EaDSItem> CREATOR = new Creator<EaDSItem>() {
        @Override
        public EaDSItem createFromParcel(Parcel in) {
            EaDSItem item = new EaDSItem();

            item.magnitude = (Double) in.readValue(null);
            item.place = in.readString();
            item.url = in.readString();
            item.time = (Long) in.readValue(null);
            double[] long__coords = in.createDoubleArray();
            if (long__coords != null)
                item.long_ = new GeoPoint(long__coords);
            item.id = in.readString();
            return item;
        }

        @Override
        public EaDSItem[] newArray(int size) {
            return new EaDSItem[size];
        }
    };

}


