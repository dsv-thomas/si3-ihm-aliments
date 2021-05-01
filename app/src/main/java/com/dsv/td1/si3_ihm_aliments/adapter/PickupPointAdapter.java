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
import com.dsv.td1.si3_ihm_aliments.consumer.PickupPoint;

import java.util.List;

public class PickupPointAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<PickupPoint> listView;
    private IAdapterListener listener;

    public PickupPointAdapter(Context contexte, List listView) {
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
            maVue = mInflater.inflate(R.layout.pickuppoint_layout, null);
        }

       Log.d("RESERVATION", String.valueOf(listView.size()));

        TextView place = maVue.findViewById(R.id.pickupPointReservationLayout);
        TextView date = maVue.findViewById(R.id.datePickupPointLayout);
        TextView schedule = maVue.findViewById(R.id.schedulePickupPointReservation);

        place.setText(listView.get(position).getPlace());
        date.setText(listView.get(position).getDate().toString());
        schedule.setText(listView.get(position).getSchedule().toString());

/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Réservation","position="+position+"listener"+ listener);
            //    if(listener!=null) listener.onButtonShowPopupWindowClick(v, listView.get(position));
            }
        });

*/
        return maVue;
    }

    public void addListener(IAdapterListener aListener) {
        listener = aListener;
    }
}