package com.dsv.td1.si3_ihm_aliments.adapter;

import android.content.Context;
import android.content.ContextWrapper;
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
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<Reservation> listView;
    private IAdapterListener listener;

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

        TextView nom = maVue.findViewById(R.id.nameProductReservation);
        TextView id = maVue.findViewById(R.id.idReservation);
        TextView quantity = maVue.findViewById(R.id.quantityProductReservation);
        TextView unitPrice = maVue.findViewById(R.id.priceProductReservation);
        TextView totalPrice = maVue.findViewById(R.id.priceReservation);
        TextView place = maVue.findViewById(R.id.pickupPointReservationLayout);
        TextView date = maVue.findViewById(R.id.datePickupPointReservation);
        TextView time = maVue.findViewById(R.id.timePickupPointReservation);
        ImageView imageView = maVue.findViewById(R.id.imageProduct);
        Button button = maVue.findViewById(R.id.deleteReservation);



        nom.setText(listView.get(position).getProduct().getName());
        id.setText(listView.get(position).getId().toString());
        unitPrice.setText(String.valueOf(listView.get(position).getProduct().getPricePerKg()));
        quantity.setText(String.valueOf(listView.get(position).getQuantity()));
        int countTotalPrice = listView.get(position).getQuantity() * Integer.parseInt(listView.get(position).getProduct().getPricePerKg());
        totalPrice.setText(String.valueOf(countTotalPrice));
        place.setText(listView.get(position).getPickupPoint().getPlace());

        date.setText(listView.get(position).getPickupPoint().getDate().toString());
        time.setText(listView.get(position).getPickupPoint().getSchedule().toString());

        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(ImagesHelper.getDirName(contexte), listView.get(position).getProduct().getImageName()));


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null) listener.deleteReservation(listView.get(position));
            }
        });

        return maVue;
    }

    public void addListener(IAdapterListener aListener) {
        listener = aListener;
    }

    public void updateList(List<Reservation> list) {
        listView = list;
    }
}