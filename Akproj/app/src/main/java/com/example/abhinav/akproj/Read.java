package com.example.abhinav.akproj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Read extends AppCompatActivity {

    TextView sender_name;
    TextView subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        sender_name = (TextView) findViewById(R.id.sender_name);
        subject = (TextView) findViewById(R.id.textView);

        Intent senderdetails = getIntent();
        String sender = senderdetails.getStringExtra("DETAILS");
        StringTokenizer tokenizer = new StringTokenizer(sender,"|");
        /*String[] sender_info = sender.split("|");*/
        sender_name.setText(tokenizer.nextToken());
        subject.setText(subject.getText()+" : " + tokenizer.nextToken());


    }
    public void onclickdel1(View view) {
        Intent c = new Intent(this, MainActivity.class);
        startActivity(c);
    }

    public void onClickReply(View view) {
        Intent c = new Intent(this, Compose.class);
        startActivity(c);
    }
}
