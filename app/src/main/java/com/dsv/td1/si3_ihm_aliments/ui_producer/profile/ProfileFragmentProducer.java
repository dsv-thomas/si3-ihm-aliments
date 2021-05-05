package com.dsv.td1.si3_ihm_aliments.ui_producer.profile;

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
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class ProfileFragmentProducer extends Fragment implements Observer {
    IProducerListener listener;
    PickupPointAdapter pickupPointAdapter;
    TextView nameProducerProfile;
    TextView locationProducerPage;
    TextView telNumberProducerPage;
    TextView isbio;
    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile_producer_for_producer, container, false);
        Model_Producer.getInstance().addObserver(this);

        listener = (IProducerListener) getActivity();
        nameProducerProfile = root.findViewById(R.id.nameProducerProfil);
        locationProducerPage = root.findViewById(R.id.locationProducerPage);
        telNumberProducerPage = root.findViewById(R.id.telNumberProducerPage);
        isbio = root.findViewById(R.id.buttonBioProfil);
        imageView = root.findViewById(R.id.avatarProducer);


        Button button = root.findViewById(R.id.submitForm);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSettingsClicked();
            }
        });

        nameProducerProfile.setText(Model_Producer.getInstance().getCurrentProducer().getName());
        locationProducerPage.setText(Model_Producer.getInstance().getCurrentProducer().getPlace());
        telNumberProducerPage.setText(Model_Producer.getInstance().getCurrentProducer().getpNumber());
        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(getActivity(), ImagesHelper.getDirName(getActivity()), Model_Producer.getInstance().getCurrentProducer().getUuid().toString()));
        if(Model_Producer.getInstance().getCurrentProducer().isBio()) {
            isbio.setText("Producteur Bio");
        } else {
            isbio.setText("Producteur non Bio");
        }


        ListView listView = root.findViewById(R.id.listPickupPointProducer);

        pickupPointAdapter = new PickupPointAdapter(getContext(), Model_Producer.getInstance().getCurrentProducer().getPickupPoints());

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
    public void update(Observable o, Object arg) {
        if(arg != null) {
            if(arg.toString().contains("addpickupoint")) {
                pickupPointAdapter.updateList(Model_Producer.getInstance().getCurrentProducer().getPickupPoints());
                pickupPointAdapter.notifyDataSetChanged();
            }
            //UpdateProfile
            if(arg.toString().contains("profile")) {
                nameProducerProfile.setText(Model_Producer.getInstance().getCurrentProducer().getName());
                locationProducerPage.setText(Model_Producer.getInstance().getCurrentProducer().getPlace());
                telNumberProducerPage.setText(Model_Producer.getInstance().getCurrentProducer().getpNumber());
                if(Model_Producer.getInstance().getCurrentProducer().isBio()) {
                    isbio.setText("Producteur Bio");
                } else {
                    isbio.setText("Producteur non Bio");
                }
                imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(getActivity(), ImagesHelper.getDirName(getActivity()), Model_Producer.getInstance().getCurrentProducer().getUuid().toString()));
            }
        }
    }
}