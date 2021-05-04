package com.dsv.td1.si3_ihm_aliments.ui_consumer.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerListener;
import com.dsv.td1.si3_ihm_aliments.adapter.ReservationAdapter;
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;

import java.util.Observable;
import java.util.Observer;

public class ProfileFragmentConsumer extends Fragment implements Observer {
    IConsumerListener listener;
    ReservationAdapter reservationAdapter;
    TextView textView;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_consumer, container, false);
        Model_Consumer.getInstance().addObserver(this);
        listener = (IConsumerListener) getActivity();

        textView = root.findViewById(R.id.nameConsumerPage);
        imageView = root.findViewById(R.id.avatarConsumer);

        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(getActivity(), ImagesHelper.getDirName(getActivity()), Model_Consumer.getInstance().getConsumerList().get(0).getUuid().toString()));
        textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());

        Button button = root.findViewById(R.id.submitForm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsClicked();
            }
        });

        ListView listView = root.findViewById(R.id.consumer_reservation);
        reservationAdapter = new ReservationAdapter(getContext(), Model_Consumer.getInstance().getConsumerList().get(0).getReservations());
        reservationAdapter.addListener((IAdapterListener) getActivity());
        listView.setAdapter(reservationAdapter);

        return root;
    }

    @Override
    public void update(Observable o, Object arg) {
        reservationAdapter.updateList(Model_Consumer.getInstance().getConsumerList().get(0).getReservations());
        reservationAdapter.notifyDataSetChanged();

        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(getActivity(), ImagesHelper.getDirName(getActivity()), Model_Consumer.getInstance().getConsumerList().get(0).getUuid().toString()));
        textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());
    }
}