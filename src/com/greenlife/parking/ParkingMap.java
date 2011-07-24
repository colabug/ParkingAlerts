package com.greenlife.parking;

import android.graphics.drawable.Drawable;
import com.google.android.maps.*;
import android.os.Bundle;

import java.util.List;

public class ParkingMap extends MapActivity
{

    private MapView       mapView;
    private List<Overlay> mapOverlays;
    private AlertOverlay  parkingAlerts;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        // Create zoom-able map
        mapView = (MapView) findViewById( R.id.mapview );
        mapView.setBuiltInZoomControls( true );

        // Get overlays and set image
        mapOverlays = mapView.getOverlays();
        createAlertOverlays();
        createCarOverlays();
    }

    private void createAlertOverlays()
    {
        // Create new alert & add to map
        Drawable drawable = this.getResources().getDrawable( R.drawable.androidmarker );
        parkingAlerts = new AlertOverlay( drawable, this );
        GeoPoint point = new GeoPoint( 19240000, -99120000 );
        OverlayItem overlayItem = new OverlayItem( point,
                                                   "Hola, Mundo!",
                                                   "I'm in Mexico City!" );

        // Add alert to map and overlay list
        parkingAlerts.addOverlay( overlayItem );
        mapOverlays.add( parkingAlerts );
    }

    private void createCarOverlays()
    {
        // Note: Image obtained from http://gettyicons.com/free-icon/108/transport2-icon-set/free-cabriolet-red-icon-png/
        // License: Commercial Use Allowed, Back Link Required
        Drawable drawable = this.getResources().getDrawable( R.drawable.car_marker );

        // Create new alert & add to map
        CarOverlay cars = new CarOverlay( drawable, this );
        GeoPoint point = new GeoPoint( 35410000, 139460000 );
        OverlayItem overlayItem = new OverlayItem( point,
                                                   "Sekai, konichiwa!",
                                                   "I'm in Japan!" );

        // Add car to map and overlay list
        cars.addOverlay( overlayItem );
        mapOverlays.add( cars );
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}
