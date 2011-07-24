package com.greenlife.parking;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

public class AlertOverlay extends ItemizedOverlay {

    // Holds parking alerts
    private ArrayList<OverlayItem> alerts = new ArrayList<OverlayItem>();
    private Context mContext;

    // Creates an alert with a default marker.
    public AlertOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }

    // Creates an alert with a default marker & context.
    public AlertOverlay(Drawable defaultMarker, Context context) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
    }

    // Adds overlay and prepares them for drawing.
    public void addOverlay(OverlayItem overlay) {
        alerts.add(overlay);
        populate();
    }

    @Override
    protected OverlayItem createItem(int i) {
        return alerts.get(i);
    }

    @Override
    public int size() {
        return alerts.size();
    }

    @Override
    protected boolean onTap(int index) {
        // Get the tapped item
        OverlayItem item = alerts.get(index);

        // Build an interactive dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());
        dialog.show();

        return true;
    }
}
