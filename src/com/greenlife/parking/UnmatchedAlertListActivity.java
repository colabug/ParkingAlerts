package com.greenlife.parking;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class UnmatchedAlertListActivity extends ListActivity
{
    private static final String TAG = UnmatchedAlertListActivity.class.getSimpleName();

    // UI
    private ListView listView;
    String[] alertList;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        Log.d( TAG, "onCreate" );

        // Set the list & properties
        alertList = getResources().getStringArray( R.array.UNMATCHED_ALERTS );
        setListAdapter( new ArrayAdapter<String>( this,
                                                  android.R.layout.simple_list_item_1,
                                                  alertList ) );
        listView = getListView();
        listView.setTextFilterEnabled( true );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            public void onItemClick( AdapterView<?> parent,
                                     View view,
                                     int position,
                                     long id )
            {
                startActivity( AlertDetailActivity.createIntent(
                UnmatchedAlertListActivity.this ) );
            }
        } );
    }

    public static Intent createIntent( Context context )
    {
        return new Intent( context, UnmatchedAlertListActivity.class );
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
