package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Compose extends AppCompatActivity {

    EditText select_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        select_contact =(EditText)findViewById(R.id.select_contact);

        select_contact.setOnLongClickListener(select_contact_intent);

    }
    public void onclicksave(View view)
    {
        Intent d= new Intent(this,MainActivity.class);
        startActivity(d);
    }

    public View.OnLongClickListener select_contact_intent = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Intent contactlist = new Intent(getApplicationContext(),Contacts.class);
            startActivity(contactlist);
            return false;
        }
    };
}


