package com.dsv.td1.si3_ihm_aliments.ui_consumer.producer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ProducerAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.util.Observable;
import java.util.Observer;

public class ProducerDescriptionFragmentConsumer extends Fragment implements Observer {

    private static final int MY_PERMISSION_REQUEST_CODE_CALL_PHONE = 555;

    ProductAdapter productAdapter;
    PickupPointAdapter pickupPointAdapter;
    ProducerAdapter producerAdapter;
    private Producer producer;
    private IAdapterListener listener;

    public ProducerDescriptionFragmentConsumer(Producer producer) {
        this.producer = producer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listener = (IAdapterListener) getActivity();
        Model_Producer.getInstance().addObserver(this);
        View root = inflater.inflate(R.layout.fragment_profile_producer_for_consumer, container, false);
        TextView textViewName = root.findViewById(R.id.nameProducerPage);
        textViewName.setText(producer.getName());
        TextView textViewLocation = root.findViewById(R.id.locationProducerPage);
        textViewLocation.setText(producer.getPlace());
        TextView bioProfil = root.findViewById(R.id.bioProfil);
        if(Model_Producer.getInstance().getProducerList().get(0).isBio()) {
            bioProfil.setText("Producteur Bio");
        } else {
            bioProfil.setText("Producteur non Bio");
        }
        ImageView imageView = root.findViewById(R.id.avatarProducer);
        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(getActivity(), ImagesHelper.getDirName(getActivity()), producer.getUuid().toString()));
        TextView textViewPhoneNumber = root.findViewById(R.id.phoneNumberProducerPage);
        textViewPhoneNumber.setText(producer.getpNumber());

        ImageView imageViewPriceIcon = root.findViewById(R.id.priceIconProducerPage);
        int iconNumber = producer.getIconNumber();
        if(iconNumber == 1) {
            imageViewPriceIcon.setImageResource(R.drawable.euro);
        } else if(iconNumber == 2) {
            imageViewPriceIcon.setImageResource(R.drawable.euro2);
        } else {
            imageViewPriceIcon.setImageResource(R.drawable.euro3);
        }
        /*
        TextView textViewNumber = root.findViewById(R.id.telNumberProducerPage);
        textViewNumber.setText(producer.getpNumber());
         */

        ImageView imageViewPhoneIcon = root.findViewById(R.id.phoneIconProducerPage);
        imageViewPhoneIcon.setImageResource(R.drawable.phone);
        imageViewPhoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askPermissionAndCall();
            }
        });


        ListView listView1 = root.findViewById(R.id.pickupPointList);
        pickupPointAdapter = new PickupPointAdapter(getContext(), producer.getPickupPoints());
        pickupPointAdapter.addListener((IAdapterListener) getActivity());
        listView1.setAdapter(pickupPointAdapter);

        ListView listView = root.findViewById(R.id.productsList);
        productAdapter = new ProductAdapter(getContext(), producer.getProposedProducts(), producer);
        productAdapter.addListener((IAdapterListener) getActivity());
        listView.setAdapter(productAdapter);

        return root;
    }

    @Override
    public void update(Observable o, Object arg) {
        productAdapter.updateList(Model_Producer.getInstance().getProducerList().get(0).getProposedProducts());
        productAdapter.notifyDataSetChanged();

        pickupPointAdapter.updateList(Model_Producer.getInstance().getProducerList().get(0).getPickupPoints());
        pickupPointAdapter.notifyDataSetChanged();
    }

    private void askPermissionAndCall() {
        int sendSmsPermission = ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.CALL_PHONE);

        if (sendSmsPermission != PackageManager.PERMISSION_GRANTED) {
            // If don't have permission so prompt the user.
            this.requestPermissions(
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSION_REQUEST_CODE_CALL_PHONE
            );
            return;
        }
        this.callNow();
    }

    @SuppressLint("MissingPermission")
    private void callNow() {
        String phoneNumber = this.producer.getpNumber();

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        try {
            this.startActivity(callIntent);
        } catch (Exception ex) {
            Toast.makeText(this.getContext(),"Your call failed... " + ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE_CALL_PHONE: {

                // Note: If request is cancelled, the result arrays are empty.
                // Permissions granted (CALL_PHONE).
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this.getContext(), "Permission granted!", Toast.LENGTH_LONG).show();
                    this.callNow();
                }
                // Cancelled or denied.
                else {
                    Toast.makeText(this.getContext(), "Permission denied!", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }

    // When results returned
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_PERMISSION_REQUEST_CODE_CALL_PHONE) {
            if (resultCode == Activity.RESULT_OK) {
                // Do something with data (Result returned).
                Toast.makeText(this.getContext(), "Action OK", Toast.LENGTH_LONG).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this.getContext(), "Action Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.getContext(), "Action Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}