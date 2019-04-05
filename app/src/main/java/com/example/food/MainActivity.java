package com.example.food;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //  ViewFlipper viewFlipper;
    Button showMap;
    Button showMeNext;


    private Restaurant[] restaurants = {
            new Restaurant("Chipotle", 43.656896, -79.380948, R.drawable.chipotle),
            new Restaurant("Sansotei Ramen", 43.654979, -79.386509, R.drawable.ramen),
            new Restaurant("Salad King", 43.657711, -79.381651, R.drawable.saldking),
            new Restaurant("Bannock", 43.651902, -79.381123, R.drawable.bannock),
            new Restaurant("Queen St. Warehouse", 43.650169, -79.390027, R.drawable.warehouse),
            new Restaurant("Fugo Desserts", 43.654816, -79.387355, R.drawable.fugo)
    };

    private int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        showMap = (Button) findViewById(R.id.next);
        showMeNext = (Button) findViewById(R.id.previous);

        showMap.setOnClickListener(this);
        showMeNext.setOnClickListener(this);


        Bundle bundle = new Bundle();
        bundle.putInt("imagePath", restaurants[0].getImage());
        Fragment imgFrag = new RestaurantImageFragment();
        imgFrag.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragmentContainer, imgFrag, "IMG_FRAGMENT");
        transaction.commit();


    }

    //Settings_Menu Start
    private boolean isChecked = false;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem checkable = menu.findItem(R.id.item2);
        checkable.setChecked(isChecked);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                if (isChecked == true) {
                    Toast.makeText(this, "Vegetarian Only Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Vegetarian Only Not Unchecked", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.item3:
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                if (isChecked == true) {
                    Toast.makeText(this, "Healthy Only Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Healthy Only Not Unchecked", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.item4:
                isChecked = !item.isChecked();
                item.setChecked(isChecked);
                if (isChecked == true) {
                    Toast.makeText(this, "Low Price Only Checked", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Low Price Only Unchecked", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //Settings_Menu End

    @Override
    public void onClick(View v) {
        if (v == showMap) {
//            MyFragment myFragment = (MyFragment)getFragmentManager().findFragmentByTag("MY_FRAGMENT");
//            if (myFragment != null && myFragment.isVisible()) {
//                // add your code here
//            }
            GMapFragment mapFrag = (GMapFragment) getSupportFragmentManager().findFragmentByTag("MAP_FRAGMENT");
            if (mapFrag == null || !mapFrag.isVisible()) {
                Bundle bundle = new Bundle();
                bundle.putString("title", restaurants[count].getName());
                bundle.putDouble("x_coord", restaurants[count].getXCoor());
                bundle.putDouble("y_coord", restaurants[count].getYCoor());
                mapFrag = new GMapFragment();
                mapFrag.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer, mapFrag, "MAP_FRAGMENT");
                transaction.commit();
            } else {
                return;
            }
        } else if (v == showMeNext) {
            if (count + 1 < restaurants.length) {
                count += 1;
            } else {
                Toast.makeText(MainActivity.this, "no more restaurants", Toast.LENGTH_LONG).show();
                return;
            }

            RestaurantImageFragment imgFrag = (RestaurantImageFragment) getSupportFragmentManager().findFragmentByTag("IMG_FRAGMENT");
            if (imgFrag != null && imgFrag.isVisible()) {
                    imgFrag.showNextImg(restaurants[count].getImage());
            }
            else {
                Bundle bundle = new Bundle();
                bundle.putInt("imagePath", restaurants[count].getImage());
                imgFrag = new RestaurantImageFragment();
                imgFrag.setArguments(bundle);
            }
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentContainer, imgFrag, "IMG_FRAGMENT");
            transaction.commit();
        }
    }


}