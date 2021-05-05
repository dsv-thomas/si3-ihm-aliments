package com.dsv.td1.si3_ihm_aliments.ui_consumer.producer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.adapter.ProducerAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.Observable;
import java.util.Observer;

public class ProducerFragmentConsumer extends Fragment implements Observer {
    IProducerListener iProducerListener;
    ProducerAdapter producerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_producer_consumer, container, false);

        Model_Producer.getInstance().addObserver(this);

        producerAdapter = new ProducerAdapter(this.getContext(), Model_Producer.getInstance().getProducerList());
        ListView listView = root.findViewById(R.id.listViewProducer);
        listView.setAdapter(producerAdapter);

        Spinner spinner = (Spinner) root.findViewById(R.id.sortSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.sortTypes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String selectedSortType = (String) parent.getItemAtPosition(pos);
                List<Producer> initialList = Model_Producer.getInstance().getProducerList();

                if(selectedSortType.equals("Nom")) {
                    Collections.sort(initialList, (p1, p2) -> p1.getName().compareTo(p2.getName()));
                } else if(selectedSortType.equals("Prix")) {
                    Collections.sort(initialList, (p1, p2) -> Double.compare(p1.getAveragePrice(), (p2.getAveragePrice())));
                }

                Model_Producer.getInstance().setProducerList(initialList);
                producerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        producerAdapter.addListener((IAdapterListener) getContext());
        return root;
    }

    @Override
    public void update(Observable o, Object arg) {
        producerAdapter.updateList(Model_Producer.getInstance().getProducerList());
        producerAdapter.notifyDataSetChanged();
    }
}