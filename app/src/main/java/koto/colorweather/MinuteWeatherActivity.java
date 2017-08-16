package koto.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import koto.colorweather.Adapters.MinutelyWeatherAdapter;

public class MinuteWeatherActivity extends Activity {

    @BindView(R.id.recyclerMinuteWeather) RecyclerView recyclerMinuteWeather;
    @BindView(R.id.txtDataEmpty)
    TextView txtDataEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minute_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        ArrayList<Minute> minutes = intent.getParcelableArrayListExtra(MainActivity.MINUTE_ARRAY);

        if( minutes != null && !minutes.isEmpty()){
            MinutelyWeatherAdapter minutelyWeatherAdapter = new MinutelyWeatherAdapter(this,minutes);
            recyclerMinuteWeather.setAdapter(minutelyWeatherAdapter);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerMinuteWeather.setLayoutManager(layoutManager);
            recyclerMinuteWeather.setVisibility(View.VISIBLE);
            txtDataEmpty.setVisibility(View.GONE);

        }else{

            recyclerMinuteWeather.setVisibility(View.GONE);
            txtDataEmpty.setVisibility(View.VISIBLE);

        }





    }
}
