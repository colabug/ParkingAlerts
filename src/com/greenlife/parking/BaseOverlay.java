package com.greenlife.parking;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.view.MotionEvent;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

abstract public class BaseOverlay extends ItemizedOverlay
{
    // private static final String TAG = BaseOverlay.class.getSimpleName();

    private Context context;

    public BaseOverlay( Drawable defaultMarker, Context context )
    {
        super( boundCenterBottom( defaultMarker ) );
        this.context = context;
    }

    // Adds overlay and prepares it for drawing.
    abstract void addOverlay( OverlayItem overlay );

    @Override
    public boolean onTouchEvent( MotionEvent event, MapView mapView )
    {
        // Touch and release of a point
        if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            // TODO: Use this or delete it
            // Although the functionality is cool, this is kinda slow
            // Get point clicked
            // GeoPoint point = mapView.getProjection()
            //                        .fromPixels( (int) event.getX(),
            //                                     (int) event.getY() );

            // Decode point to an address string
            // String addressString = translatePointToAddress( point );
            // Log.i( TAG, "Address translated to: " + addressString );
        }

        return false;
    }

    private String translatePointToAddress( GeoPoint point )
    {
        String defaultString = "No address found.";
        Geocoder geoCoder = new Geocoder( getContext(),
                                          Locale.getDefault() );

        // Attempt to get address(es)
        List<Address> addresses;
        try
        {
            addresses = geoCoder.getFromLocation( point.getLatitudeE6() / 1E6,
                                                  point.getLongitudeE6() / 1E6,
                                                  1 );
        }
        catch ( IOException exception )
        {
            // Return default if an error occurred
            exception.printStackTrace();
            return defaultString;
        }

        // Return default if nothing found
        if ( addresses.size() <= 0 )
        {
            return defaultString;
        }

        // Construct address string
        String addressString = "";
        for ( int i = 0; i < addresses.get( 0 ).getMaxAddressLineIndex(); i++ )
        {
            addressString += addresses.get( 0 ).getAddressLine( i ) + "\n";
        }

        return addressString;
    }

    public Context getContext()
    {
        return context;
    }
}
