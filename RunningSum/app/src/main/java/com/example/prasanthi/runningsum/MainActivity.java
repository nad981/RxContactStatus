package com.example.prasanthi.runningsum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasanthi.runningsum.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**Random myRandom = new Random();

    StreamSink<Integer> nextRandom     = new StreamSink<>();

    StreamSink<Unit>    incrementEvent = new StreamSink<>();
    StreamSink<Unit>    decrementEvent = new StreamSink<>();

    // I normally wouldn't put these here, but I wanted to provide a hint
    CellLoop<Integer>            N;
    CellLoop<ArrayList<Integer>> lastNValues;
    Cell<Integer>                sum;
**/
    Button Minus, Plus, SendNumber;
    TextView Num, Numbers, Sum;
    int n = 3;
    ArrayList<Integer>list = new ArrayList<>();
    int sum=0;
    String buffer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Compute();
        Num.setText(""+n);

        Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n <=10 && n>3){
                    n--;
                    Num.setText(""+n);
                } else {
                    Toast.makeText(MainActivity.this, "N cannot be less than 3.", Toast.LENGTH_SHORT).show();
                }

                if (list.size()>n){
                    sum = sum - list.get(0);
                    buffer = buffer.substring(4);
                    list.remove(0);
                    Numbers.setText(buffer);
                }

            }
        });

        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (n>=3 && n<10){
                    n++;
                    Num.setText(""+n);
                } else {
                    Toast.makeText(MainActivity.this, "N cannot be higher than 10.", Toast.LENGTH_SHORT).show();
                }
            }
        });
       /** Button minusButton = (Button)findViewById(R.id.Minus);
        Button plusButton  = (Button)findViewById(R.id.Plus);

        final TextView valueView = (TextView)findViewById(R.id.Val);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusEvent.send(Unit.UNIT);
            }
        });
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plusEvent.send(Unit.UNIT);
            }
        });

        findViewById(R.id.Max).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                max.send(Integer.parseInt(((EditText)view).getText().toString()));
            }
        });

        findViewById(R.id.Min).setOnFocusChangeListener(new View.OnFocusChangeListener() { **/



        SendNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int num = r.nextInt(9) + 1;
                if (list.size()<n){
                    list.add(num);
                    sum = sum + num;
                    if (list.size()==1){
                        buffer = buffer + "| " + num+ " | ";
                        Numbers.setText(buffer);
                        Sum.setText(""+sum);
                    } else {
                        buffer = buffer + num + " | ";
                        Numbers.setText(buffer);
                        Sum.setText(""+sum);
                    }
                }

                else if (list.size()==n){
                    sum = sum - list.get(0);
                    buffer = buffer.substring(4);
                    list.remove(0);
                    list.add(num);
                    sum = sum + num;
                    if (list.size()==1){
                        buffer = buffer + " | " + num;
                        Numbers.setText(buffer);
                        Sum.setText(""+sum);
                    } else {
                        buffer = buffer + num + " | ";
                        Numbers.setText(buffer);
                        Sum.setText(""+sum);
                    }
                }
            }
        });




    }

    public void Compute(){
        Minus = (Button)findViewById(R.id.minus);
        Plus = (Button)findViewById(R.id.plus);
        SendNumber = (Button)findViewById(R.id.btn_send_number);

        Num = (TextView)findViewById(R.id.n);
        Numbers = (TextView)findViewById(R.id.numbers);
        Sum = (TextView)findViewById(R.id.sum);
    }

    /**public void inc_and_dec(View view) {
        Transaction.runVoid(new Runnable() {
            @Override
            public void run() {
                minusEvent.send(Unit.UNIT);
                plusEvent.send(Unit.UNIT);
            }
        });
**/


}
