package koto.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KOTO on 08/08/2017.
 */

public class Hour implements Parcelable {

    String hourWeather;
    String hourWeatherDescription;

    public Hour() {
    }

    protected Hour(Parcel in) {
        hourWeather = in.readString();
        hourWeatherDescription = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public String getHourWeather() {
        return hourWeather;
    }

    public void setHourWeather(String hourWeather) {
        this.hourWeather = hourWeather;
    }

    public String getHourWeatherDescription() {
        return hourWeatherDescription;
    }

    public void setHourWeatherDescription(String hourWeatherDescription) {
        this.hourWeatherDescription = hourWeatherDescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(hourWeather);
            parcel.writeString(hourWeatherDescription);
    }
}
