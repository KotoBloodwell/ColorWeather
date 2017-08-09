package koto.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import koto.colorweather.Adapters.DailyWeatherAdapter;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        ArrayList<Day> days = new ArrayList<Day>();
        Day day = new Day();
        day.setDayName("Monday");
        day.setDayDescriptionWeather("Party Cloudy");
        day.setDayProbability("Rain Probability: 0%");

        for ( int i=0;i<500;i++){
            days.add(day);
        }


        days.add(day);

        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(this,days);
        setListAdapter(dailyWeatherAdapter);

    }
}
