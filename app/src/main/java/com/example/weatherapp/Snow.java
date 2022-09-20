package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class Snow {
    @SerializedName("3h")
    double h3;

    public Snow(double h3) {
        this.h3 = h3;
    }

    public Snow() {

    }

    public double getH3() {
        return h3;
    }

    public void setH3(double h3) {
        this.h3 = h3;
    }
}
