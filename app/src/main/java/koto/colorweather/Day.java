package koto.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by interdynamics on 8/8/2017.
 */

public class Day implements Parcelable {

    String dayName;
    String dayDescriptionWeather;
    String dayProbability;

    public Day() {
    }

    protected Day(Parcel in) {
        dayName = in.readString();
        dayDescriptionWeather = in.readString();
        dayProbability = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDayDescriptionWeather() {
        return dayDescriptionWeather;
    }

    public void setDayDescriptionWeather(String dayDescriptionWeather) {
        this.dayDescriptionWeather = dayDescriptionWeather;
    }

    public String getDayProbability() {
        return dayProbability;
    }

    public void setDayProbability(String dayProbability) {
        this.dayProbability = dayProbability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(dayName);
        parcel.writeString(dayDescriptionWeather);
        parcel.writeString(dayProbability);

    }
}
