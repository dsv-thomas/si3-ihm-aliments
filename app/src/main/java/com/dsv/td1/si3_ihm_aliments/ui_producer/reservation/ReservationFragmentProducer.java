package com.dsv.td1.si3_ihm_aliments.ui_producer.reservation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.ProducerAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ReservationAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class ReservationFragmentProducer extends androidx.fragment.app.Fragment {

    IProducerAdapterListener iProducerAdapterListener;

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_reservation_producer, container, false);

            ReservationAdapter reservationAdapter = new ReservationAdapter(this.getContext(), Model_Consumer.getInstance().reservationsForProducer(Model_Producer.getInstance().getProducerList().get(0)));
            ListView listView = root.findViewById(R.id.listViewReservationProducer);
            listView.setAdapter(reservationAdapter);

            reservationAdapter.addListener((IAdapterListener) getContext());
            return root;
        }

    }
