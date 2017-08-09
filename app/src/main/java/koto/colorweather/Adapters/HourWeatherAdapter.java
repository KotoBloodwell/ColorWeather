package koto.colorweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import koto.colorweather.Hour;
import koto.colorweather.R;

/**
 * Created by KOTO on 08/08/2017.
 */

public class HourWeatherAdapter extends BaseAdapter {

    ArrayList<Hour> hours = new ArrayList<Hour>();
    Context context;

    public HourWeatherAdapter(Context context, ArrayList<Hour> hours) {
        this.context = context;
        this.hours = hours;
    }

    @Override

    public int getCount() {
        return hours.size();
    }

    @Override
    public Object getItem(int position) {
        return hours.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
    ViewHolder viewHolder;
        Hour hour = hours.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.hour_list_item,null);
            viewHolder = new ViewHolder();

            viewHolder.txtHourWeather = (TextView) view.findViewById(R.id.txtTitleHourly);
            viewHolder.txtHourWeatherDescription = (TextView) view.findViewById(R.id.txtDescriptionHourly);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtHourWeather.setText(hour.getHourWeather());
        viewHolder.txtHourWeatherDescription.setText(hour.getHourWeatherDescription());

        return view;
    }

    static class ViewHolder{

        TextView txtHourWeather;
        TextView txtHourWeatherDescription;

    }
}
