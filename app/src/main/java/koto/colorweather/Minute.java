package koto.colorweather;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KOTO on 09/08/2017.
 */

public class Minute implements Parcelable {

    String minute;
    String description;

    public Minute() {
    }

    protected Minute(Parcel in) {
        minute = in.readString();
        description = in.readString();
    }

    public static final Creator<Minute> CREATOR = new Creator<Minute>() {
        @Override
        public Minute createFromParcel(Parcel in) {
            return new Minute(in);
        }

        @Override
        public Minute[] newArray(int size) {
            return new Minute[size];
        }
    };

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(minute);
        dest.writeString(description);
    }
}
