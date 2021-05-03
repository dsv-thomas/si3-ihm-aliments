package com.dsv.td1.si3_ihm_aliments.ui_consumer.producer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Maraiche;
import com.dsv.td1.si3_ihm_aliments.producer.Poissonnier;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.util.Observable;
import java.util.Observer;

public class ProducerDescriptionFragmentConsumer extends Fragment implements Observer {

    private Producer producer;
    private IAdapterListener listener;
    ProductAdapter productAdapter;
    PickupPointAdapter pickupPointAdapter;

    public ProducerDescriptionFragmentConsumer(Producer producer) {
        this.producer = producer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listener = (IAdapterListener) getActivity();
        Model_Producer.getInstance().addObserver(this);
        View root = inflater.inflate(R.layout.fragment_profile_producer_for_consumer, container, false);
        TextView textView = root.findViewById(R.id.nameProducerPage);
        textView.setText(producer.getName());

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
}