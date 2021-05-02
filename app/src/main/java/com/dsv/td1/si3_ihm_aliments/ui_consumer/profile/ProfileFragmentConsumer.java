package com.dsv.td1.si3_ihm_aliments.ui_consumer.profile;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
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

public class ProfileFragmentConsumer extends Fragment {
    IConsumerListener listener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_consumer, container, false);
        listener = (IConsumerListener) getActivity();
        TextView textView = root.findViewById(R.id.nameConsumerPage);
        ImageView imageView = root.findViewById(R.id.avatarConsumer);
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

        textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());

        ListView listView = root.findViewById(R.id.consumer_reservation);
        ReservationAdapter reservationAdapter = new ReservationAdapter(getContext(), Model_Consumer.getInstance().getConsumerList().get(0).getReservations());
        reservationAdapter.addListener((IAdapterListener) getActivity());
        listView.setAdapter(reservationAdapter);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RESUME", "RESUME");
        //textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());
    }

}

