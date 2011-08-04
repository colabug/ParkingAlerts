package com.greenlife.parking;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.os.Bundle;

import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.maps.*;

import java.util.List;

public class ParkingMap extends MapActivity
{
    // Logging
    private static final String TAG = ParkingMap.class.getSimpleName();

    // Constants
    private static final int DEFAULT_ZOOM_LEVEL = 16;

    // Map
    private MapView       mapView;
    private MapController mapController;
    private List<Overlay> mapOverlays;

    // Location
    private MyLocationOverlay myLocationOverlay;

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
        Log.d( TAG, "onCreate" );

        // Create zoom-able map
        setContentView( R.layout.main );
        mapView = (MapView) findViewById( R.id.mapview );
        mapView.setBuiltInZoomControls( true );

        // Get controller and overlays
        mapController = mapView.getController();
        mapOverlays = mapView.getOverlays();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d( TAG, "onResume" );

        // Show current location on the map
        myLocationOverlay = new MyLocationOverlay( this, mapView );
        myLocationOverlay.enableMyLocation();
        mapOverlays.add( myLocationOverlay );

        // Show starting map location & set zoom
        mapController.setZoom( DEFAULT_ZOOM_LEVEL );
        focusMapViewOnCurrentLocation( getString( R.string.UNABLE_TO_FIND_DEFAULT_LOCATION_SHOW_DEFAULT ) );

        // Get overlays & refresh
        createAlertOverlays();
        createCarOverlays();
        mapView.invalidate();
    }

    private void focusMapViewOnCurrentLocation( String errorMessage )
    {
        boolean success = focusOnCurrentLocation( true );
        if ( !success )
        {
            Log.w( TAG, errorMessage );
            Toast.makeText( this,
                            errorMessage,
                            Toast.LENGTH_SHORT ).show();
        }
    }

    private boolean focusOnCurrentLocation( boolean useDefaultOnFailure )
    {
        GeoPoint currentLocation = myLocationOverlay.getMyLocation();
        if ( currentLocation != null )
        {
            mapView.getController().animateTo( currentLocation );
        }
        else if ( useDefaultOnFailure )
        {
            Log.w( TAG, "Showing default location." );
            MapUtilities.navigateToSearchedLocation( getString( R.string.DEFAULT_LOCATION_STRING ),
                                                     this );
        }
        else
        {
            return false;
        }

        return true;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d( TAG, "onPause" );

        myLocationOverlay.disableMyLocation();
    }

    private void createAlertOverlays()
    {
        // Create new alert & add to map
        alertMarker = this.getResources().getDrawable( R.drawable.alert_marker );
        parkingAlerts = new AlertOverlay( alertMarker, this );
        GeoPoint point = new GeoPoint( TestCoordinates.ALERT_LATITIUDE,
                                       TestCoordinates.ALERT_LONGITUDE );
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
        GeoPoint point = new GeoPoint( TestCoordinates.CAR_LATITIUDE,
                                       TestCoordinates.CAR_LONGITUDE );
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
    public boolean onOptionsItemSelected( MenuItem item )
    {
        switch ( item.getItemId() )
        {
            // Show unmatched alerts list
            case R.id.unmatched:
                startActivity( UnmatchedAlertListActivity.createIntent( this ) );
                break;

            // Focus on current location
            case R.id.location:
                focusMapViewOnCurrentLocation( getString( R.string.UNABLE_TO_FIND_DEFAULT_LOCATION ) );
                break;
        }
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

    public MapView getMapView()
    {
        return mapView;
    }

    public MapController getMapController()
    {
        return mapController;
    }
}
