package com.dsv.td1.si3_ihm_aliments.ui_consumer.producer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Poisson;


public class ProducerDescriptionFragment extends Fragment {

    private Producer producer;
    private IProducerAdapterListener listener;

    public ProducerDescriptionFragment(Producer producer) {
        this.producer = producer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listener = (IProducerAdapterListener) getActivity();
        View root = inflater.inflate(R.layout.fragment_profile_producer_for_consumer, container, false);
        TextView textView = root.findViewById(R.id.nameProducerPage);
        textView.setText(producer.getName());

        //
        producer.addProducts(new Poisson("Poisson", "4", "4"));
        //

        ListView listView1 = root.findViewById(R.id.pickupPointList);
        PickupPointAdapter pickupPointAdapter = new PickupPointAdapter(getContext(), producer.getPickupPoints());
        pickupPointAdapter.addListener((IConsumerAdapterListener) getActivity());
        listView1.setAdapter(pickupPointAdapter);

        ListView listView = root.findViewById(R.id.productsList);
        ProductAdapter productAdapter = new ProductAdapter(getContext(), producer.getProposedProducts(), producer);
        productAdapter.addListener((IConsumerAdapterListener) getActivity());
        listView.setAdapter(productAdapter);

/*
        root.findViewById(R.id.backProducerPage).setOnClickListener(v -> {
            Log.d("BACK","BACK");
            listener.onButtonClicked(BACK);
        });
*/


        return root;
    }
}
