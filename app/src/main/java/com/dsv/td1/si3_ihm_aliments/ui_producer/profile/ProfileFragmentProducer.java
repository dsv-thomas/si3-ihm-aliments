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
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class ProfileFragmentProducer extends Fragment {
    IProducerAdapterListener listener;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile_producer_for_producer, container, false);

        listener = (IProducerAdapterListener) getActivity();
        TextView nameProducerProfil = root.findViewById(R.id.nameProducerProfil);
        TextView locationProducerPage = root.findViewById(R.id.locationProducerPage);
        TextView telNumberProducerPage = root.findViewById(R.id.telNumberProducerPage);
        ImageView imageView = root.findViewById(R.id.avatarProducer);
        ContextWrapper cw = new ContextWrapper(getContext());
        String directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();
        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(directoryName, Model_Producer.getInstance().getProducerList().get(0).getUuid().toString()));

        Button button = root.findViewById(R.id.submitForm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsClicked();
            }
        });

        nameProducerProfil.setText(Model_Producer.getInstance().getProducerList().get(0).getName());
        locationProducerPage.setText(Model_Producer.getInstance().getProducerList().get(0).getPlace());
        telNumberProducerPage.setText(Model_Producer.getInstance().getProducerList().get(0).getpNumber());

        ListView listView = root.findViewById(R.id.listPickupPointProducer);

        PickupPointAdapter pickupPointAdapter = new PickupPointAdapter(getContext(), Model_Producer.getInstance().getProducerList().get(0).getPickupPoints());

        listView.setAdapter(pickupPointAdapter);

        Button addPickupoint = root.findViewById(R.id.addNewPickupPoint);

        addPickupoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonShowPopupAddPickupPointClick(getView());
            }
        });
        //pickupPointAdapter.addListener((IConsumerAdapterListener) getContext());


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("RESUME", "RESUME");
        //textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());
    }

}