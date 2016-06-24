package com.example.prasanthi.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.content.Context;
import android.content.SharedPreferences;

public class MyActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.prasanthi.myfirstapp.MESSAGE";
    public final static String UNSENT_MESSAGE = "com.example.prasanthi.myfirstapp.UNSENT";

    SharedPreferences sharedPreferences;

    public final static String PREFERENCES = "SettingPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        editText.setText("");
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        EditText editText;
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        String message= sharedPreferences.getString(UNSENT_MESSAGE,null);
        editText= (EditText)findViewById(R.id.edit_message);
        editText.setText(message);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText editText;
        editText= (EditText)findViewById(R.id.edit_message);
        String message= editText.getText().toString();
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UNSENT_MESSAGE,message);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
