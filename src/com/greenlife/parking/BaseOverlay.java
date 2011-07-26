package com.greenlife.parking;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

abstract public class BaseOverlay extends ItemizedOverlay
{
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
        if ( event.getAction() == MotionEvent.ACTION_UP )
        {
            GeoPoint point = mapView.getProjection()
                                    .fromPixels( (int) event.getX(),
                                                 (int) event.getY() );
            Toast.makeText( context,
                            point.getLatitudeE6() / 1E6 + "," +
                            point.getLongitudeE6() / 1E6,
                            Toast.LENGTH_SHORT ).show();
        }
        return false;
    }

    public Context getContext()
    {
        return context;
    }
}
