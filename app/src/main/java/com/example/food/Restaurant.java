package com.example.food;

import android.media.Image;

public class Restaurant {

    private String name;

    private Double x_coor;

    private Double y_coor;

    private int foodImg;

    public String getName() {
        return name;
    }

    public double getXCoor() {
        return x_coor;
    }

    public double getYCoor() {
        return y_coor;
    }

    public int getImage() {
        return foodImg;
    }

    public Restaurant(String _name, double _x, double _y, int _image) {
        name = _name;
        x_coor = _x;
        y_coor = _y;
        foodImg = _image;
    }

}
