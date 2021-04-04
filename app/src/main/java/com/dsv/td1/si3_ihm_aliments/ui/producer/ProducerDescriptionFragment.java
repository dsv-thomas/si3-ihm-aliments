package com.dsv.td1.si3_ihm_aliments.ui.producer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.model.Producer;

public class ProducerDescriptionFragment extends Fragment {

    private Producer producer;

    public ProducerDescriptionFragment(Producer producer) {
        this.producer = producer;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_producer_page, container, false);
        TextView textView = root.findViewById(R.id.nameProducerPage);
        textView.setText(producer.getName());
        Log.d("PRODUCER","position="+producer);

       return root;
    }
}
