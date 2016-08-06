package com.example.prasanthi.projectstep2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.audiofx.BassBoost;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    ListView mListView;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mListView = (ListView) findViewById(R.id.listview);

        mAdapter = new SimpleCursorAdapter(getBaseContext(),
                R.layout.listview_item_layout,
                null,
                new String[]{CustomerDB.KEY_CODE, CustomerDB.KEY_NAME, CustomerDB.KEY_PHONE},
                new int[]{R.id.code, R.id.name, R.id.phone}, 0);

        mListView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, this);
        generateKeyPair();
        getKeyPair();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri uri = Customer.CONTENT_URI;
        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
        mAdapter.swapCursor(arg1);
    }

    

    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
        mAdapter.swapCursor(null);
    }

    public void generateKeyPair() {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt("PrivateKey", 5);
        editor.putInt("PublicKey", 6);
        editor.commit();
    }
    public void getKeyPair() {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int nPrivateKey = prefs.getInt("PrivateKey", 0);
        int nPublicKey = prefs.getInt("PublicKey", 0);

        Toast.makeText(MainActivity.this, "PrivateKey="+nPrivateKey, Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, "PublicKey="+nPublicKey, Toast.LENGTH_LONG).show();
    }
    public void contacts(View view)
    {
        Intent i= new Intent(this,Contacts.class);
        startActivity(i);
    }

    public void settings(View view)
    {
        Intent j= new Intent(this,Settings.class);
        startActivity(j);
    }

    public void compose(View view)
    {
        Intent k = new Intent(this,Compose.class);
        startActivity(k);
    }

}





