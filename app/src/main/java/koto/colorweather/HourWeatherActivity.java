package koto.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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
        ButterKnife.bind(this);

        Intent intent = getIntent();
        ArrayList<Hour> hours  = intent.getParcelableArrayListExtra(MainActivity.HOURLY_ARRAY);


        HourWeatherAdapter hourWeatherAdapter = new HourWeatherAdapter(this,hours);
        listViewHour.setAdapter(hourWeatherAdapter);
    }
}
