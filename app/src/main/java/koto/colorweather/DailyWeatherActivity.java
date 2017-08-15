package koto.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import koto.colorweather.Adapters.DailyWeatherAdapter;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        Intent intent = getIntent();
        ArrayList<Day> days = intent.getParcelableArrayListExtra(MainActivity.DAILY_ARRAYLIST);




        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(this,days);
        setListAdapter(dailyWeatherAdapter);

    }
}
