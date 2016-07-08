package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


    }
    public void onclickdel(View view) {
        Intent a = new Intent(this, Contacts.class);
        startActivity(a);
    }

    public void onclicksav(View view) {
        Intent b = new Intent(this, Contacts.class);
        startActivity(b);
    }

}

