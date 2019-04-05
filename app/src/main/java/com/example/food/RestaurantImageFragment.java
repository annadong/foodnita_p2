package com.example.food;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantImageFragment extends Fragment {

    //add new restaurants here!

    ImageView resImage;

    public RestaurantImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        int img= getArguments().getInt("imagePath");

        View view = inflater.inflate(R.layout.fragment_restaurant_image, container, false);
        resImage = (ImageView) view.findViewById(R.id.foodImg);
        resImage.setImageResource(img);

        return view;
    }

    public void showNextImg(int img) {
        resImage.setImageResource(img);
    }

}
