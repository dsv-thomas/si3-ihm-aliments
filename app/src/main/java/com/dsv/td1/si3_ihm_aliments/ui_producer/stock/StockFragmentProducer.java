package com.dsv.td1.si3_ihm_aliments.ui_producer.stock;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.dsv.td1.si3_ihm_aliments.R;

import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;

public class StockFragmentProducer extends Fragment {
    IProducerAdapterListener iProducerAdapterListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("oooooooooooo", "ok");

        View root = inflater.inflate(R.layout.fragment_stock_producer, container, false);


        return root;
    }
}
