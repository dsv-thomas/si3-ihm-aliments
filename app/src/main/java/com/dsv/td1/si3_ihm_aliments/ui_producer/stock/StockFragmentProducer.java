package com.dsv.td1.si3_ihm_aliments.ui_producer.stock;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener;
import com.dsv.td1.si3_ihm_aliments.adapter.IProducerListener;
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;

import java.util.Observable;
import java.util.Observer;

public class StockFragmentProducer extends Fragment implements Observer {
    IProducerListener iProducerListener;
    ProductAdapter productAdapter;
    ListView listView1;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        iProducerListener = (IProducerListener) getActivity();
        Model_Producer.getInstance().addObserver(this);

        View root = inflater.inflate(R.layout.fragment_stock_producer, container, false);
        listView1 = root.findViewById(R.id.listStockForProducer);

        stockListView();
        Button addProduct = root.findViewById(R.id.addProductProducer);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iProducerListener.onAddProductClicked();
            }
        });
        return root;
    }

    public void stockListView() {
        productAdapter = new ProductAdapter(getContext(), Model_Producer.getInstance().getProducerList().get(0).getProposedProducts(), Model_Producer.getInstance().getProducerList().get(0));
        productAdapter.addListener((IAdapterListener) getActivity());
        listView1.setAdapter(productAdapter);
    }

    @Override
    public void update(Observable o, Object arg) {
        productAdapter.updateList(Model_Producer.getInstance().getProducerList().get(0).getProposedProducts());
        productAdapter.notifyDataSetChanged();
    }
}
