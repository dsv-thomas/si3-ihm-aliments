package com.dsv.td1.si3_ihm_aliments.ui_producer.profile;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class ProfileFragmentProducer extends Fragment {
    IProducerAdapterListener listener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile_producer_for_producer, container, false);

        listener = (IProducerAdapterListener) getActivity();
        TextView textView = root.findViewById(R.id.nameProducerProfil);
        ImageView imageView = root.findViewById(R.id.avatarProducer);
        ContextWrapper cw = new ContextWrapper(getContext());
        String directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();
        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(directoryName, Model_Consumer.getInstance().getConsumerList().get(0).getUuid().toString()));

        Button button = root.findViewById(R.id.submitForm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsClicked();
            }
        });

        textView.setText(Model_Producer.getInstance().getProducerList().get(0).getName());

 //       ListView listView = root.findViewById(R.id.consumer_reservation);
  //      ReservationAdapter reservationAdapter = new ReservationAdapter(getContext(), Model_Consumer.getInstance().getConsumerList().get(0).getReservations());


//        listView.setAdapter(reservationAdapter);

        //productAdapter.addListener((IConsumerAdapterListener) getContext());


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RESUME", "RESUME");
        //textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());
    }

}