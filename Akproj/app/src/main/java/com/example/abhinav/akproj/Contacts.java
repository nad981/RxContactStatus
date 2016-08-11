package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.msg_container,new ContactsFragment()).commit();
        }

    }

    public void onclickaddcon(View view)
    {
        Intent h = new Intent(this,Contact.class);
        startActivity(h);
    }
}
