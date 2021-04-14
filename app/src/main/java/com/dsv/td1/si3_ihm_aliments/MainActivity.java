package com.dsv.td1.si3_ihm_aliments;

import android.content.Intent;
import android.os.Bundle;

import com.dsv.td1.si3_ihm_aliments.controller.ControllerActivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.start_client).setOnClickListener(click-> {
            Intent intent = new Intent(getApplicationContext(),ControllerActivity.class);
            startActivity(intent);
        });

    }

}


