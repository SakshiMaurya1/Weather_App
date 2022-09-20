package com.example.weatherapp;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    LocationFetcher fetcher;
    TextView city,tempView, weather_desc, datetime;
    double latitude,longitude;
    RecyclerView recyclerView;
    DrawerLayout drawer;
    WeatherAdapter adapter;
    ImageView city2;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    double lat;
    double lon;
    double temp;
    long[] datetext =new long[100];
    String weatherdesc;
    List<ListItem> wLists;
    String API_id="92290cca26afa14bc69f6b6691130e67";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city=findViewById(R.id.city);
        tempView=findViewById(R.id.temp);
        Log.e("TEMPVIEW", "onCreate: "+tempView);
        drawer=findViewById(R.id.drawer);
        city2=findViewById(R.id.city2);
        recyclerView=findViewById(R.id.recyclerView);
        weather_desc=findViewById(R.id.weather_desc);
        datetime=findViewById(R.id.datetime);
        navigationView=findViewById(R.id.nav);
        toolbar=findViewById(R.id.toolbar1);

        toggle=new ActionBarDrawerToggle(this, drawer,toolbar,R.string.Open,R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);

        toggle.setToolbarNavigationClickListener(view -> {
            drawer.openDrawer(GravityCompat.END);
        });
        toggle.setHomeAsUpIndicator(R.drawable.navlist);
        toggle.syncState();
        Toolbar.LayoutParams layoutParams=new Toolbar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity=Gravity.END;
        city2.setOnClickListener(view ->  {
            CityFragment cityFragment=new CityFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.drawer,cityFragment).commit();
        });

        fetcher=new LocationFetcher(MainActivity.this);
        check_Loc_permissions();

        double a_lat = getIntent().getDoubleExtra("lat",0);
            Log.e("A_LAT", "onCreate: " + a_lat);
        double a_lon=getIntent().getDoubleExtra("lon",0);

//        RetrofitFetch retrofitFetch=new RetrofitFetch(latitude,longitude,API_id);
        getData(lat,lon);



    }

    private void setLayout(List<ListItem> dataa) {


        Log.e("SAKSHI===", "setLayout: "+dataa );
        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(RecyclerView.HORIZONTAL);
        adapter = new WeatherAdapter(dataa);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adapter);
    }

    void check_Loc_permissions(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
            ActivityCompat.requestPermissions( this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},10);
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},20);
        }
        else
        {
            if(fetcher.canGetLocation())
            {
                latitude=fetcher.getLatitude();
                longitude=fetcher.getLongitude();
                String city_str=fetcher.getCityfromLocation(this);
                city.setText(city_str);
            }
            else{
                fetcher.showSettingsAlert();
            }
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(permissions[0].equals(ACCESS_COARSE_LOCATION)){
            Log.e("TAG", "onRequestPermissionsResult: "+grantResults[0] );
            if(grantResults[0]==-1)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setTitle("Permission is denied!");
                builder.setMessage("Please allow Location access");
                builder.setPositiveButton("Ok", (dialog, which) -> {
                    dialog.dismiss();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", this.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.startActivity(intent);
                });
                builder.setNegativeButton("cancel", (dialog, i) -> dialog.dismiss());

                builder.show();
            }
        }
    }

    void getData(double lat, double lon)
    {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);

        String lat_str=Double.toString(lat);
        Log.e("Sakshi", "show: "+lat_str);
        String lon_str=Double.toString(lon);
        Log.e("Sakshi", "show: "+lon_str);
        Call<WeatherAPIResponse> call=retrofitAPI.getWeatherData(lat_str,lon_str,API_id);

        call.enqueue(new Callback<WeatherAPIResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherAPIResponse> call, @NonNull Response<WeatherAPIResponse> response) {
                if(!response.isSuccessful()) {
                    return;
                }
                Log.e("Asmita==>", "onResponse: ====>" +response.body());
                assert response.body() != null;
                wLists=response.body().getList();
                if(wLists==null)
                {
                    Log.e("TAG", "onResponse: ====>>>>> NULLL" );
                }
                Log.e("SUNRISE", "onResponse:===> "+response.body().getCity().getSunrise());

                int i=0;
                assert wLists != null;
                Log.e("TAG SAKSHI", "onResponse:=== "+wLists );
                for(ListItem list: wLists)
                {

                    temp=list.getMain().getTemp();
                    List<WeatherItem> weatherItemList=list.getWeather();
                    datetext[i]=list.getDt();

                    for(WeatherItem weatherItem: weatherItemList)
                    {
                        weatherdesc=weatherItem.getDescription();
                    }
                    i++;
                }
                temp-=273.15;
                int tem=(int)temp;
                Log.e("CHECKTEMPVIEW", "onResponse: "+tempView);
                tempView.setText(String.valueOf(tem));
                weather_desc.setText(weatherdesc);
                Date date = new Date(datetext[0]*1000L);
                SimpleDateFormat jdf = new SimpleDateFormat(" hh:mm a, dd/MMMM");
                String java_date = jdf.format(date);
                datetime.setText(String.valueOf(java_date));
                setLayout(wLists);
            }

            @Override
            public void onFailure(@NonNull Call<WeatherAPIResponse> call, @NonNull Throwable t) {
                Log.e("SAKSHI MAURYA","onFailure==>" +t.getMessage());
            }
        });

    }
}