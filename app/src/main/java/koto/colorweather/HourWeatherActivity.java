package koto.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import koto.colorweather.Adapters.HourWeatherAdapter;

public class HourWeatherActivity extends Activity {

@BindView(R.id.listViewHour) ListView listViewHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour_weather);
        ArrayList<Hour> hours = new ArrayList<Hour>();
        ButterKnife.bind(this);

        Hour hour = new Hour();
        hour.setHourWeather("20:00");
        hour.setHourWeatherDescription("Clear");


        for ( int i=0;i<500;i++){
            hours.add(hour);
        }


        HourWeatherAdapter hourWeatherAdapter = new HourWeatherAdapter(this,hours);
        listViewHour.setAdapter(hourWeatherAdapter);
    }
}
