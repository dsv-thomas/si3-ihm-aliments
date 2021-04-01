package com.dsv.td1.si3_ihm_aliments.ui.producer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.ProducerAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class ProducerFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_producer, container, false);

        ProducerAdapter producerAdapter = new ProducerAdapter(this.getContext(), Model_Producer.getInstance().getProducerList());
        ListView listView = root.findViewById(R.id.listViewProducer);
        listView.setAdapter(producerAdapter);
        return root;
    }
}