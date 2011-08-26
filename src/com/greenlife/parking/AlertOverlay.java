package com.greenlife.parking;

/**
 * Author: Corey Leigh Latislaw
 * Date: 8/5/11
 * Purpose:
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class AlertOverlay extends BaseOverlay
{
    // Holds parking alerts
    private ArrayList<OverlayItem> alerts = new ArrayList<OverlayItem>();

    // Creates an alert with a default marker & context.
    public AlertOverlay( Drawable defaultMarker, Context context )
    {
        super( defaultMarker, context );
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

        Toast.makeText( super.getContext(),
                        item.getTitle() + " " +
                        item.getSnippet(),
                        Toast.LENGTH_SHORT ).show();

        return true;
    }
}

