package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null)
        getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment()).commit();
    }

    public void onclick1(View view)
    {
        Intent i= new Intent(this,Contacts.class);
        startActivity(i);
    }

    public void onclick2(View view)
    {
        Intent j= new Intent(this,Settings.class);
        startActivity(j);
    }

    public void onclick3(View view)
    {
        Intent k = new Intent(this,Compose.class);
        startActivity(k);
    }

    /*public void onclickttl1(View view)
    {
        Intent k = new Intent(this,Read.class);
        startActivity(k);
    }

    public void onclickttl2(View view)
    {
        Intent k = new Intent(this,Read.class);
        startActivity(k);
    }

    public void onclickttl3(View view)
    {
        Intent k = new Intent(this,Read.class);
        startActivity(k);
    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
