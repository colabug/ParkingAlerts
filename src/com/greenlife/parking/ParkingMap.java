package com.greenlife.parking;

import com.google.android.maps.*;
import android.os.Bundle;

public class ParkingMap extends MapActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}
