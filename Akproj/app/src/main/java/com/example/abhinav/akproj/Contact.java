package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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



        Toast.makeText(Contact.this, "Encrypted Message", Toast.LENGTH_LONG).show();
    }
}

