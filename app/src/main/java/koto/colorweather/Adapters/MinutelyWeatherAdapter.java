package koto.colorweather.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import koto.colorweather.Minute;
import koto.colorweather.R;

/**
 * Created by KOTO on 09/08/2017.
 */

public class MinutelyWeatherAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Minute> minutes;

    public MinutelyWeatherAdapter(Context context, ArrayList<Minute> minutes) {
        this.context = context;
        this.minutes = minutes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.minute_list_item,parent,false);


        MinuteViewHolder minuteViewHolder = new MinuteViewHolder(view);
        return minuteViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MinuteViewHolder)holder).onBind(position);
    }

    @Override
    public int getItemCount() {
        return minutes.size();
    }

    class MinuteViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitleMinutelyWeather;
        TextView txtDescriptionMinutelyWeather;


        public MinuteViewHolder(View itemView) {
            super(itemView);

            txtTitleMinutelyWeather = (TextView) itemView.findViewById(R.id.txtTitleMinutelyWeather);
            txtDescriptionMinutelyWeather = (TextView) itemView.findViewById(R.id.txtDescriptionMinutelyWeather);

        }

        public void onBind(int position){
            Minute minute =  minutes.get(position);

            txtTitleMinutelyWeather.setText(minute.getMinute());
            txtDescriptionMinutelyWeather.setText(minute.getDescription());

        }

    }
}
