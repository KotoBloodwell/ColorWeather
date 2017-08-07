package koto.colorweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.txtHighTemp) TextView txtHighTemp;
    @BindView(R.id.txtLowTemp)  TextView txtLowTemp;
    @BindView(R.id.txtCurrentTemp)  TextView txtCurrentTemp;
    @BindView(R.id.iconImageView)   ImageView iconImageView;

    @BindView(R.id.txtDescription) TextView txtDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

   /*     CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setCurrentTemperature("30");
        currentWeather.setDescription("Sunny");
        currentWeather.setHighestTemperature("H:32º");
        currentWeather.setLowestTemperature("L:20º");
        currentWeather.setIconImage("sunny");


        txtCurrentTemp.setText(currentWeather.getCurrentTemperature());
        txtHighTemp.setText(currentWeather.getHighestTemperature());
        txtLowTemp.setText(currentWeather.getLowestTemperature());
        iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());*/


        String url ="https://api.darksky.net/forecast/4be64bdb33639191039fa64a0ac7d8df/6.244203,-75.58121189999997";
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
                            txtCurrentTemp.setText(currentWeather.getCurrentTemperature());
                            txtHighTemp.setText(currentWeather.getHighestTemperature());
                            txtLowTemp.setText(currentWeather.getLowestTemperature());
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());

                        }catch(org.json.JSONException exception){

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //mTextView.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);

    }

    private CurrentWeather getCurrentWeatherFromJson(String json)throws JSONException{

        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject("currently");

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject("daily");

        JSONArray jsonArrayDaily = jsonWithDailyWeather.getJSONArray("data");

        JSONObject jsonTodayWeather = jsonArrayDaily.getJSONObject(0);


        String summary = jsonWithCurrentWeather.getString("summary");
        String currentTemperature = Math.round(jsonWithCurrentWeather.getDouble("temperature")) + "";
        String maxTemp =  "H:" + Math.round(jsonTodayWeather.getDouble("temperatureMax"))+ "°";
        String minTemp = "L:" + Math.round(jsonTodayWeather.getDouble("temperatureMin"))+ "°";;
        String icon = jsonWithCurrentWeather.getString("icon");
        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setDescription(summary);
        currentWeather.setCurrentTemperature(currentTemperature);
        currentWeather.setHighestTemperature(maxTemp);
        currentWeather.setLowestTemperature(minTemp);
        currentWeather.setIconImage(icon);
        return currentWeather;
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
