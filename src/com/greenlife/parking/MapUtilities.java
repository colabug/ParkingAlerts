package com.greenlife.parking;

/**
 * Author: Corey Leigh Latislaw
 * Date: 8/5/11
 * Purpose:
 */

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import com.google.android.maps.GeoPoint;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapUtilities
{
    // Logging
    private static final String TAG = MapUtilities.class.getSimpleName();

    // Searches for and navigates to a for a point on the map.
    public static void navigateToSearchedLocation( String locationString,
                                                   ParkingMap parkingMap )
    {
        GeoPoint point = getLocationForAddress( locationString, parkingMap );

        if ( point != null )
        {
            Log.d( TAG, "point = " + point );
            parkingMap.getMapController().animateTo( point );
            parkingMap.getMapView().invalidate();
        }
    }

    public static GeoPoint getLocationForAddress( String searchString,
                                                  ParkingMap parkingMap )
    {
        Log.d( TAG, "Searching for: " + searchString );
        Geocoder geoCoder = new Geocoder( parkingMap, Locale.getDefault() );

        try
        {
            List<Address> addresses;
            addresses = geoCoder.getFromLocationName( searchString, 5 );

            if ( addresses.size() > 0 )
            {
                int latitude = (int) ( addresses.get( 0 ).getLatitude() * 1E6 );
                int longitude = (int) ( addresses.get( 0 ).getLongitude() * 1E6 );
                return new GeoPoint( latitude, longitude );
            }
        }
        catch ( IOException exception )
        {
            exception.printStackTrace();
        }

        return null;
    }
}
