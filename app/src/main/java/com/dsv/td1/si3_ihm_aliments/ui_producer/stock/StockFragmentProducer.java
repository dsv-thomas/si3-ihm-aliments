package com.dsv.td1.si3_ihm_aliments.ui_producer.stock;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.dsv.td1.si3_ihm_aliments.R;

import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IConsumerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.PickupPointAdapter;
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

public class StockFragmentProducer extends Fragment {
    IProducerAdapterListener iProducerAdapterListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        iProducerAdapterListener = (IProducerAdapterListener) getActivity();

        View root = inflater.inflate(R.layout.fragment_stock_producer, container, false);


        ListView listView1 = root.findViewById(R.id.listStockForProducer);
        ProductAdapter productAdapter = new ProductAdapter(getContext(), Model_Producer.getInstance().getProducerList().get(0).getProposedProducts(), Model_Producer.getInstance().getProducerList().get(0));
        productAdapter.addListener((IAdapterListener) getActivity());
        listView1.setAdapter(productAdapter);


        Button addProduct = root.findViewById(R.id.addProductProducer);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iProducerAdapterListener.onAddProductClicked();
            }
        });

        return root;
    }
}
