package com.greenlife.parking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class AlertDetailActivity extends Activity
{
    private static final String TAG = AlertDetailActivity.class.getSimpleName();

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        Log.d( TAG, "onCreate" );

        // Set up view
        setContentView( R.layout.alert_detail );
    }

    public static Intent createIntent( Context context )
    {
        return new Intent( context, AlertDetailActivity.class );
    }

    @Override
    public boolean onSearchRequested()
    {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu )
    {
        super.onCreateOptionsMenu( menu );
        return true;
    }
}
