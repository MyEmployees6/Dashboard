package com.example.dashboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton nr1;
    ImageButton nr2;
    ImageButton nr3;
    ImageButton nr4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nr1 = (ImageButton) findViewById(R.id.nr1);
        nr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, piepage.class);
                startActivity(i);
            }
        });

        nr2 = (ImageButton) findViewById(R.id.nr2);
        nr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, linepage.class);
                startActivity(i);
            }
        });

        nr3 = (ImageButton) findViewById(R.id.nr3);
        nr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, barpage.class);
                startActivity(i);
            }
        });

        nr4 = (ImageButton) findViewById(R.id.nr4);
        nr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, horizontalpage.class);
                startActivity(i);
            }
        });

    }
}