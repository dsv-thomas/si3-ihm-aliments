package com.dsv.td1.si3_ihm_aliments.ui_consumer.profile;

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
import com.dsv.td1.si3_ihm_aliments.adapter.ProductAdapter;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;

public class ProfileFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_consumer_page, container, false);
        TextView textView = root.findViewById(R.id.nameConsumerPage);

        textView.setText(Model_Consumer.getInstance().getConsumerList().get(0).getName());

        ListView listView = root.findViewById(R.id.consumer_reservation);
        ProductAdapter productAdapter = new ProductAdapter(getContext(), Model_Consumer.getInstance().getConsumerList().get(0).getReservation());


        listView.setAdapter(productAdapter);

        //productAdapter.addListener((IConsumerAdapterListener) getContext());
        return root;
    }
}