package com.dsv.td1.si3_ihm_aliments.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dsv.td1.si3_ihm_aliments.R;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;

import java.util.List;

public class ReservationAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<Reservation> listView;
    private IConsumerAdapterListener listener;

    public ReservationAdapter(Context contexte, List listView) {
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
            maVue = mInflater.inflate(R.layout.reservation_layout, null);
        }

       Log.d("RESERVATION", String.valueOf(listView.size()));
        Log.d("RESERVATION", String.valueOf(listView.size()));

        TextView nom = maVue.findViewById(R.id.nameProductReservation);
        TextView id = maVue.findViewById(R.id.idReservation);
        TextView quantity = maVue.findViewById(R.id.quantityProductReservation);
        TextView place = maVue.findViewById(R.id.pickupPointReservation);
        ImageView imageView = maVue.findViewById(R.id.imageProduct); //TODO: image
        Button button = maVue.findViewById(R.id.reservation); //TODO: ANNULER

        //imageView.setImageResource(); //TODO: product picture
        nom.setText(listView.get(position).getProduct().getName());
        id.setText(listView.get(position).getId().toString());
        quantity.setText(String.valueOf(listView.get(position).getQuantity()));
        place.setText(listView.get(position).getPickupPoint());
        //place.setText(listView.get(position).getPlace()); //TODO: place picture


/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Réservation","position="+position+"listener"+ listener);
            //    if(listener!=null) listener.onButtonShowPopupWindowClick(v, listView.get(position));
            }
        });

*/

        maVue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADAPTER","position="+position+"listener"+ listener);

                if (listener!=null) listener.onClickProduct(position);

            }
        });
        return maVue;
    }

    public void addListener(IConsumerAdapterListener aListener) {
        listener = aListener;
    }
}