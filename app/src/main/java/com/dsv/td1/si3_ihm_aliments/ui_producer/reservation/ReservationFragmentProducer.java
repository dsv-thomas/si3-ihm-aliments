package com.dsv.td1.si3_ihm_aliments.ui_producer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.adapter.ReservationAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

import java.util.Observable;
import java.util.Observer;

public class ReservationFragmentProducer extends androidx.fragment.app.Fragment implements Observer {


    IProducerListener iProducerListener;
    ReservationAdapter reservationAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_reservation_producer, container, false);
        Model_Consumer.getInstance().addObserver(this);

        reservationAdapter = new ReservationAdapter(this.getContext(), Model_Consumer.getInstance().reservationsForProducer(Model_Producer.getInstance().getProducerList().get(0)));
        ListView listView = root.findViewById(R.id.listViewReservationProducer);

        reservationAdapter.addListener((IAdapterListener) getActivity());
        listView.setAdapter(reservationAdapter);

        return root;
    }

    @Override
    public void update(Observable o, Object arg) {
        reservationAdapter.updateList(Model_Consumer.getInstance().getConsumerList().get(0).getReservations());
        reservationAdapter.notifyDataSetChanged();
    }
}