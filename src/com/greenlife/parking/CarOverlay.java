package com.greenlife.parking;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class CarOverlay extends ItemizedOverlay {
    // Stores parked cars
    private ArrayList<OverlayItem> cars = new ArrayList<OverlayItem>();

    private Context mContext;

    // Creates a car with a default marker.
    public CarOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }

    // Creates an car with a default marker & context.
    public CarOverlay(Drawable defaultMarker, Context context) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
    }

    // Adds overlay and prepares them for drawing.
    public void addOverlay(OverlayItem overlay) {
        cars.add(overlay);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
        return cars.get(i);
    }

    @Override
    public int size() {
        return cars.size();
    }

    @Override
    protected boolean onTap(int index) {
        // Get the tapped item
        OverlayItem item = cars.get(index);

        // Build an interactive dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();

        return true;
    }
}
