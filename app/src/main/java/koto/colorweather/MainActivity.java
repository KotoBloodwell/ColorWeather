package koto.colorweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.txtDailyWeather) void txtDailyWeather() {
        Intent intentDaily = new Intent(MainActivity.this,DailyWeatherActivity.class);
        startActivity(intentDaily);
    }

    @OnClick(R.id.txtHourlyWeather) void txtHourlyWeather() {
        Intent intentHourly = new Intent(MainActivity.this,HourWeatherActivity.class);
        startActivity(intentHourly);
    }

    @OnClick(R.id.txtMinutelyWeather) void txtMinutelyWeather() {
        Intent intentMinute = new Intent(MainActivity.this,MinuteWeather.class);
        startActivity(intentMinute);
    }
}
