package com.dsv.td1.si3_ihm_aliments.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;

import java.util.List;

public class ProducerAdapter extends BaseAdapter {
    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<Producer> listView;
    private IProducerAdapterListener listener;

    public ProducerAdapter(Context contexte, List listView) {
        this.listView = listView;
        this.contexte = contexte;
    }

    @Override
    public int getCount() {
        return listView.size();
    }

    @Override
    public Object getItem(int position) {
        return getItemId(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View maVue = convertView;
        if (maVue == null) {
            mInflater = (LayoutInflater) contexte.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            maVue = mInflater.inflate(R.layout.producer_layout, null);
        }

        TextView nom = maVue.findViewById(R.id.nameProducer);
        TextView place = maVue.findViewById(R.id.placeProducer);
        TextView pNumber = maVue.findViewById(R.id.PnumberProducer);
        ImageView imageView = maVue.findViewById(R.id.avatarProducer); //TODO: image

        //imageView.setImageResource(); //TODO: Profile picture
        nom.setText(listView.get(position).getName());
        place.setText(listView.get(position).getPlace());
        pNumber.setText(listView.get(position).getpNumber());

        maVue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADAPTER","position="+position+"listener"+ listener);

                if (listener!=null) listener.onClickProducer(position);

            }
        });
        return maVue;
    }

    public void addListener(IProducerAdapterListener aListener) {
        listener = aListener;
    }
}