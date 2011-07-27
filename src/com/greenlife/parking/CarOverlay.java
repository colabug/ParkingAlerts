package com.greenlife.parking;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class CarOverlay extends BaseOverlay
{
    // Stores parked cars
    private ArrayList<OverlayItem> cars = new ArrayList<OverlayItem>();


    // Creates an car with a default marker & context.
    public CarOverlay( Drawable defaultMarker, Context context )
    {
        super( defaultMarker, context );
    }

    // Adds overlay and prepare it for drawing.
    public void addOverlay( OverlayItem overlay )
    {
        cars.add( overlay );
        populate();
    }

    @Override
    protected OverlayItem createItem( int i )
    {
        return cars.get( i );
    }

    @Override
    public int size()
    {
        return cars.size();
    }

    @Override
    protected boolean onTap( int index )
    {
        // Get the tapped item
        OverlayItem item = cars.get( index );

        // Show toast
        Toast.makeText( super.getContext(),
                        item.getTitle() + " " +
                        item.getSnippet(),
                        Toast.LENGTH_SHORT ).show();

        return true;
    }
}
