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
import com.dsv.td1.si3_ihm_aliments.helpers.ImageLoadHelper;
import com.dsv.td1.si3_ihm_aliments.model.Model_Consumer;
import com.dsv.td1.si3_ihm_aliments.model.Model_Producer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<Product> listView;
    private IConsumerAdapterListener listener;
    private Producer currentProducer;

    public ProductAdapter(Context contexte, List listView) {
        this.listView = listView;
        this.contexte = contexte;
    }

    public ProductAdapter(Context contexte, List listView, Producer currentProducer) {
        this.contexte = contexte;
        this.listView = listView;
        this.currentProducer = currentProducer;
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
            maVue = mInflater.inflate(R.layout.product_layout, null);
        }

        TextView nom = maVue.findViewById(R.id.nameProduct);
        TextView place = maVue.findViewById(R.id.pickupPointReservationLayout);
        ImageView imageView = maVue.findViewById(R.id.imageProduct);
        Button button = maVue.findViewById(R.id.reservation);
        ContextWrapper cw = new ContextWrapper(contexte);
        String directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();
        imageView.setImageBitmap(ImageLoadHelper.loadImageFromStorage(directoryName, currentProducer.getProposedProducts().get(position).getImageName()));
        nom.setText(listView.get(position).getName());
        //place.setText(listView.get(position).getPlace()); //TODO: place picture

        //Remove reservation button
        if(parent.toString().contains("consumer_reservation")) {
            button.setVisibility(View.GONE);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Réservation","position="+position+"listener"+ listener);
                if(listener!=null) listener.onButtonShowPopupWindowClick(v, listView.get(position), currentProducer);
            }
        });



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