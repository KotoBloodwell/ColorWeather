package koto.colorweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import koto.colorweather.Day;
import koto.colorweather.R;

/**
 * Created by interdynamics on 8/8/2017.
 */

public class DailyWeatherAdapter extends BaseAdapter{

    ArrayList<Day> days;
    Context context;

    public DailyWeatherAdapter(Context context, ArrayList<Day> days) {
        this.context = context;
        this.days = days;
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
       return days.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Day day = days.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.daily_list_item,null);


            viewHolder = new ViewHolder();

            viewHolder.dayTitle = (TextView) view.findViewById(R.id.txtTitleDaily);
            viewHolder.dayDescription = (TextView) view.findViewById(R.id.txtDescriptionDaily);
            viewHolder.txtProbabilityDaily = (TextView) view.findViewById(R.id.txtProbabilityDaily);


            view.setTag(viewHolder);

        }else{
        viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.dayTitle.setText(day.getDayName());
        viewHolder.dayDescription.setText(day.getDayDescriptionWeather());
        viewHolder.txtProbabilityDaily.setText(day.getDayProbability());


        return view;
    }

    static class ViewHolder{
        TextView dayTitle;
        TextView dayDescription;
        TextView txtProbabilityDaily;
    }
}
