package com.greenlife.parking;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class AlertOverlay extends ItemizedOverlay
{
    // Holds parking alerts
    private ArrayList<OverlayItem> alerts = new ArrayList<OverlayItem>();

    private Context mContext;

    // Creates an alert with a default marker.
    public AlertOverlay( Drawable defaultMarker )
    {
        super( boundCenterBottom( defaultMarker ) );
    }

    // Creates an alert with a default marker & context.
    public AlertOverlay( Drawable defaultMarker, Context context )
    {
        super( boundCenterBottom( defaultMarker ) );
        mContext = context;
    }

    // Adds overlay and prepares it for drawing.
    public void addOverlay( OverlayItem overlay )
    {
        alerts.add( overlay );
        populate();
    }

    @Override
    protected OverlayItem createItem( int i )
    {
        return alerts.get( i );
    }

    @Override
    public int size()
    {
        return alerts.size();
    }

    @Override
    protected boolean onTap( int index )
    {
        // Get the tapped item
        OverlayItem item = alerts.get( index );

        Toast.makeText( mContext,
                        item.getTitle() + " " +
                        item.getSnippet(),
                        Toast.LENGTH_SHORT ).show();

        return true;
    }

    @Override
    public boolean onTouchEvent( MotionEvent event, MapView mapView )
    {
        if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            GeoPoint point = mapView.getProjection()
                                    .fromPixels( (int) event.getX(),
                                                 (int) event.getY() );
            Toast.makeText( mContext,
                            point.getLatitudeE6() / 1E6 + "," +
                            point.getLongitudeE6() / 1E6,
                            Toast.LENGTH_SHORT ).show();
        }
        return false;
    }
}

