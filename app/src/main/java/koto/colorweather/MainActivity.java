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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
    public static final String MINUTELY = "minutely";
    public static final String DATA = "data";
    public static final String TIME = "time";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String SUMMARY = "summary";
    public static final String HOURLY = "hourly";
    public static final String DAILY = "daily";
    public static final String CURRENTLY = "currently";
    public static final String ICON = "icon";
    public static final String TEMPERATURE_MIN = "temperatureMin";
    public static final String TEMPERATURE_MAX = "temperatureMax";
    public static final String TEMPERATURE = "temperature";
    public static final String DAILY_ARRAYLIST = "DAILY_ARRAYLIST";
    public static final String HOURLY_ARRAY = "HOURLY_ARRAY";
    public static final String MINUTE_ARRAY = "MINUTE_ARRAY";
    @BindView(R.id.txtHighTemp) TextView txtHighTemp;
    @BindView(R.id.txtLowTemp)  TextView txtLowTemp;
    @BindView(R.id.txtCurrentTemp)  TextView txtCurrentTemp;
    @BindView(R.id.iconImageView)   ImageView iconImageView;

    @BindView(R.id.txtDescription) TextView txtDescription;

    ArrayList<Day> days;
    ArrayList<Hour> hours;
    ArrayList<Minute> minutes;

    final static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

   /*     CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setCurrentTemperature("30");
        currentWeather.setHourWeatherDescription("Sunny");
        currentWeather.setHighestTemperature("H:32º");
        currentWeather.setLowestTemperature("L:20º");
        currentWeather.setIconImage("sunny");


        txtCurrentTemp.setText(currentWeather.getCurrentTemperature());
        txtHighTemp.setText(currentWeather.getHighestTemperature());
        txtLowTemp.setText(currentWeather.getLowestTemperature());
        iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());*/




       // String url ="https://api.darksky.net/forecast/4be64bdb33639191039fa64a0ac7d8df/6.244203,-75.58121189999997?units=si";
      String url = "https://api.darksky.net/forecast/4be64bdb33639191039fa64a0ac7d8df/37.8267,-122.423?units=si";
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
                            txtCurrentTemp.setText(currentWeather.getCurrentTemperature());
                            txtHighTemp.setText(String.format("H: %sº",currentWeather.getHighestTemperature()));
                            txtLowTemp.setText(String.format("H: %sº",currentWeather.getLowestTemperature()));
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());


                            days = getDailyWeatherFromJson(response);


                       hours = getHourWeatherFromJson(response);

 minutes = getMinuteWeatherFromJson(response);



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
        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject(CURRENTLY);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonArrayDaily = jsonWithDailyWeather.getJSONArray(DATA);


        JSONObject jsonTodayWeather = jsonArrayDaily.getJSONObject(0);


        String summary = jsonWithCurrentWeather.getString(SUMMARY);
        String currentTemperature = Math.round(jsonWithCurrentWeather.getDouble(TEMPERATURE)) + "";
        String maxTemp = Math.round(jsonTodayWeather.getDouble(TEMPERATURE_MAX))+ "";
        String minTemp = Math.round(jsonTodayWeather.getDouble(TEMPERATURE_MIN))+ "";;
        String icon = jsonWithCurrentWeather.getString(ICON);
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
        intentDaily.putParcelableArrayListExtra(DAILY_ARRAYLIST,days);
        startActivity(intentDaily);
    }

    @OnClick(R.id.txtHourlyWeather) void txtHourlyWeather() {
        Intent intentHourly = new Intent(MainActivity.this,HourWeatherActivity.class);
        intentHourly.putParcelableArrayListExtra(HOURLY_ARRAY,hours);
        startActivity(intentHourly);
    }

    @OnClick(R.id.txtMinutelyWeather) void txtMinutelyWeather() {
        Intent intentMinute = new Intent(MainActivity.this,MinuteWeatherActivity.class);
        intentMinute.putParcelableArrayListExtra(MINUTE_ARRAY,minutes);
        startActivity(intentMinute);
    }

    private ArrayList<Day> getDailyWeatherFromJson(String json)throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat("EEEE");
        ArrayList<Day> days = new ArrayList<Day>();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject(CURRENTLY);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonArrayDaily = jsonWithDailyWeather.getJSONArray(DATA);

        for(int i = 0; i < jsonArrayDaily.length();i++){
                Day day = new Day();
            JSONObject jsonWithDayData = jsonArrayDaily.getJSONObject(i);

            String rainProbability = "Rain Probability: " +jsonWithDayData.getDouble(PRECIP_PROBABILITY) + "%";
            String description = jsonWithDayData.getString(SUMMARY);
            String dayName = dateFormat.format(jsonWithDayData.getLong(TIME)*1000) ;

                day.setDayName(dayName);
                day.setDayDescriptionWeather(description);
                day.setDayProbability(rainProbability);
            days.add(day);

        }

        return days;
    }
    private ArrayList<Hour> getHourWeatherFromJson(String json)throws JSONException{
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        ArrayList<Hour> hours = new ArrayList<Hour>();
        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithHourlyWeather = jsonObject.getJSONObject(HOURLY);

        JSONArray jsonArrayHourly = jsonWithHourlyWeather.getJSONArray(DATA);

        for(int i = 0; i < jsonArrayHourly.length();i++){
            Hour hour = new Hour();
            JSONObject jsonWithHourData = jsonArrayHourly.getJSONObject(i);

            String description = jsonWithHourData.getString(SUMMARY);
            String hourTime = dateFormat.format(jsonWithHourData.getLong(TIME)*1000) ;

            hour.setHourWeather(hourTime);
            hour.setHourWeatherDescription(description);

            hours.add(hour);

        }

        return hours;
    }

    private ArrayList<Minute> getMinuteWeatherFromJson(String json)throws JSONException{
            DateFormat dateformat = new SimpleDateFormat("HH:mm");
        dateformat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
            JSONObject jsonObject  = new JSONObject(json);

        JSONObject jsonMinuteWeather = jsonObject.getJSONObject(MINUTELY);

        JSONArray jsonArrayMinuteWeather = jsonMinuteWeather.getJSONArray(DATA);

        ArrayList<Minute> minutes = new ArrayList<>();
        for(int i = 0; i < jsonArrayMinuteWeather.length()-1 ; i++){
            Minute minute = new Minute();
            JSONObject jsonMinuteData = jsonArrayMinuteWeather.getJSONObject(i);

            String minuteWeather = dateformat.format(jsonMinuteData.getDouble(TIME)*1000);
            String descriptionMinute = "Rain Probability: " + jsonMinuteData.getDouble(PRECIP_PROBABILITY) * 100 + "%";

            minute.setMinute(minuteWeather);
            minute.setDescription(descriptionMinute);
            minutes.add(minute);

        }
   return minutes;
    }

    }
