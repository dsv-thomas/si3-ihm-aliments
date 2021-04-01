package com.dsv.td1.si3_ihm_aliments.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.ProducerAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class ControllerActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_controller);
        ProducerAdapter producerAdapter = new ProducerAdapter(this, Model_Producer.getInstance().getProducerList());
        ListView listView = findViewById(R.id.listViewProducer);
        listView.setAdapter(producerAdapter);
        //producerAdapter.addListener(this); //TODO: Listener for producerAdapter
        Log.d("ControllerActivity","nb observers=");
    }
}
