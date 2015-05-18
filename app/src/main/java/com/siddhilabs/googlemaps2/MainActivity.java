package com.siddhilabs.googlemaps2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.StreetViewPanoramaOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends FragmentActivity implements
        OnMapReadyCallback, OnStreetViewPanoramaReadyCallback {

    protected GoogleMap googleMap;
    protected Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*final MapFragment normalMapFragment = MapFragment.newInstance();
        normalMapFragment.getMapAsync(this);
        normalMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.mapContainer, normalMapFragment);
                fragmentTransaction.commit();

            }
        });*/

    }

    public void normalMapButtonHandler(View view) {
        MapFragment normalMapFragment = MapFragment.newInstance(getNormalMapOptions());
        normalMapFragment.getMapAsync(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }
        fragmentTransaction.add(R.id.mapContainer, normalMapFragment);
        fragmentTransaction.commit();
        currentFragment = normalMapFragment;

    }

    public void liteMapButtonHandler(View view) {
        MapFragment liteMapFragment = MapFragment.newInstance(getLiteMapOptions());
        liteMapFragment.getMapAsync(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }

        fragmentTransaction.add(R.id.mapContainer, liteMapFragment);
        fragmentTransaction.commit();
        currentFragment = liteMapFragment;

    }

    public void streetViewButtonHandler(View view) {
        StreetViewPanoramaFragment streetViewPanoramaFragment = StreetViewPanoramaFragment.
                newInstance(getStreetViewOptions());
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        if(currentFragment != null) {
            fragmentTransaction.remove(currentFragment);
        }

        fragmentTransaction.add(R.id.mapContainer, streetViewPanoramaFragment);
        fragmentTransaction.commit();
        currentFragment = streetViewPanoramaFragment;
    }

    private GoogleMapOptions getLiteMapOptions() {
        GoogleMapOptions liteMapOptions = new GoogleMapOptions();
        liteMapOptions.compassEnabled(false);
        liteMapOptions.mapToolbarEnabled(false);
        liteMapOptions.mapType(GoogleMap.MAP_TYPE_NORMAL);
        liteMapOptions.liteMode(true);

        return liteMapOptions;
    }

    private GoogleMapOptions getNormalMapOptions() {
        GoogleMapOptions normalMapOptions = new GoogleMapOptions();
        normalMapOptions.compassEnabled(true);
        normalMapOptions.mapToolbarEnabled(true);
        normalMapOptions.mapType(GoogleMap.MAP_TYPE_NORMAL);


        return normalMapOptions;
    }
    private StreetViewPanoramaOptions getStreetViewOptions() {
        StreetViewPanoramaOptions streetViewPanoramaOptions = new StreetViewPanoramaOptions();
        streetViewPanoramaOptions.position(new LatLng(-33.87365, 151.20689));
        return streetViewPanoramaOptions;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(0, 0))
                        .title("My Marker")
        );
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        //do nothing for now.
    }
}
