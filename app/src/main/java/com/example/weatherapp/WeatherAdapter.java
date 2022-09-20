package com.example.weatherapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.RecycleViewHolder>{
    List<ListItem> listItems;
    Gson gson;
    public WeatherAdapter(List<ListItem> listItem){
        this.listItems = listItem;
        Log.e("TAG", "WeatherAdapter: "+listItem );
        gson =  new GsonBuilder().setPrettyPrinting().create();
    }

    Context context;

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.weather_item_layout, parent, false);
        RecycleViewHolder viewHolder = new RecycleViewHolder(listItem);

        Log.e("ADAPTERDATA","CHECK ADAPTER DATA"+listItem);

        context = parent.getContext();

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        ListItem info = listItems.get(position);

        String i =  info.weather.get(0).description;
        holder.info.setText(Capitalize(i));
        int c_temp= (int) ((int)info.main.temp-273.15);
        String cel=String.valueOf(c_temp);
        holder.temp.setText(cel+" °C");

        Date date = new Date(info.dt*1000L);
        SimpleDateFormat jdf = new SimpleDateFormat(" hh:mm a, dd/MMMM");
        String java_date = jdf.format(date);

        holder.time.setText(java_date);

        Glide.with(context).load("https://openweathermap.org/img/wn/"+info.weather.get(0).icon+"@4x.png").into(holder.ico);
    }

    @NonNull
    private String Capitalize(String i) {
        return i.substring(0, 1).toUpperCase() + i.substring(1).toLowerCase();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder{
        public TextView info;
        public TextView time;
        public TextView temp;
        public ImageView ico;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            info = itemView.findViewById(R.id.info);
            time = itemView.findViewById(R.id.time);
            temp = itemView.findViewById(R.id.temp);
            ico = itemView.findViewById(R.id.wedIcon);
        }
    }
}
