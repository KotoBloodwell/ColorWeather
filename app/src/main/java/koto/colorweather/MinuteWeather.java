package koto.colorweather;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import koto.colorweather.Adapters.MinutelyWeatherAdapter;

public class MinuteWeather extends Activity {

    @BindView(R.id.recyclerMinuteWeather) RecyclerView recyclerMinuteWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minute_weather);
        ButterKnife.bind(this);

        ArrayList<Minute> minutes = new ArrayList<>();

        Minute minute = new Minute();
        minute.setMinute("00:00");
        minute.setDescription("95%");

        Minute minute2 = new Minute();
        minute2.setMinute("00:05");
        minute2.setDescription("85%");

        Minute minute3 = new Minute();
        minute3.setMinute("00:10");
        minute3.setDescription("60%");

        minutes.add(minute);
        minutes.add(minute2);
        minutes.add(minute3);


        MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this,minutes);
        recyclerMinuteWeather.setAdapter(minutelyWeatherAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerMinuteWeather.setLayoutManager(layoutManager);


    }
}
