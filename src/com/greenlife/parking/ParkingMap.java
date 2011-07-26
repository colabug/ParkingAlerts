package com.greenlife.parking;

import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.os.Bundle;

import com.google.android.maps.*;

import java.util.List;

public class ParkingMap extends MapActivity
{
    // Constants
    private static final int PHILADELPHIA_LATITIUDE = 39952222;
    private static final int PHILADELPHIA_LONGITUDE = -75164166;
    private static final int CAR_LATITIUDE          = 39945017;
    private static final int CAR_LONGITUDE          = -75175881;
    private static final int ALERT_LATITIUDE        = 39943000;
    private static final int ALERT_LONGITUDE        = -75174835;

    // Map
    private MapView       mapView;
    private MapController mapController;
    private List<Overlay> mapOverlays;

    // Alerts
    private AlertOverlay parkingAlerts;
    private Drawable     alertMarker;

    // Cars
    private CarOverlay cars;
    private Drawable   carMarker;


    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.main );

        // Create zoom-able map
        mapView = (MapView) findViewById( R.id.mapview );
        mapView.setBuiltInZoomControls( true );

        // Set initial zoom & location
        mapController = mapView.getController();
        mapController.animateTo( new GeoPoint( PHILADELPHIA_LATITIUDE,
                                               PHILADELPHIA_LONGITUDE ) );
        mapController.setZoom( 16 );
        mapView.invalidate();

        // Get overlays and set image
        mapOverlays = mapView.getOverlays();
        createAlertOverlays();
        createCarOverlays();
    }

    private void createAlertOverlays()
    {
        // Create new alert & add to map
        alertMarker = this.getResources().getDrawable( R.drawable.androidmarker );
        parkingAlerts = new AlertOverlay( alertMarker, this );
        GeoPoint point = new GeoPoint( ALERT_LATITIUDE, ALERT_LONGITUDE );
        OverlayItem alert = new OverlayItem( point,
                                             "I'm an alert",
                                             "... in your neighborhood!" );

        // Add alert to map and overlay list
        parkingAlerts.addOverlay( alert );
        mapOverlays.add( parkingAlerts );
    }

    private void createCarOverlays()
    {
        // Note: Image obtained from http://gettyicons.com/free-icon/108/transport2-icon-set/free-cabriolet-red-icon-png/
        // License: Commercial Use Allowed, Back Link Required
        carMarker = this.getResources().getDrawable( R.drawable.car_marker );

        // Create new alert & add to map
        cars = new CarOverlay( carMarker, this );
        GeoPoint point = new GeoPoint( CAR_LATITIUDE, CAR_LONGITUDE );
        OverlayItem car = new OverlayItem( point,
                                           "I'm a car",
                                           "... on your block!" );

        // Add car to map and overlay list
        cars.addOverlay( car );
        mapOverlays.add( cars );
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        super.onCreateOptionsMenu( menu );
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );
        return true;
    }
}
