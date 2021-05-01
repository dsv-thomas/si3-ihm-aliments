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
import com.dsv.td1.si3_ihm_aliments.helpers.ImagesHelper;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

import java.util.List;

import static com.dsv.td1.si3_ihm_aliments.adapter.IAdapterListener.ACTION_CLICK_PRODUCT;

public class ProductAdapter extends BaseAdapter {

    private static LayoutInflater mInflater = null; // Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private Context contexte;
    private List<Product> listView;
    private IAdapterListener iAdapterListener;
    private IConsumerAdapterListener iConsumerAdapterListener;
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
        Button reservationButton = maVue.findViewById(R.id.reservation);
        ContextWrapper cw = new ContextWrapper(contexte);
        String directoryName = (cw.getDir("imageDir", Context.MODE_PRIVATE)).getPath();
        imageView.setImageBitmap(ImagesHelper.loadImageFromStorage(directoryName, currentProducer.getProposedProducts().get(position).getImageName()));
        nom.setText(listView.get(position).getName());
        //place.setText(listView.get(position).getPlace()); //TODO: place picture

        //Remove reservation button
        if(parent.toString().contains("consumer_reservation")) {
            reservationButton.setVisibility(View.GONE);
        }
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Réservation","position="+position+"listener"+ iAdapterListener);
                if(iAdapterListener !=null) iAdapterListener.onButtonShowPopupWindowClick(v, listView.get(position), currentProducer);
            }
        });

        maVue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ADAPTER","position="+position+"listener"+ iAdapterListener);
                if (iAdapterListener !=null) iAdapterListener.onClickItemListView(position, ACTION_CLICK_PRODUCT);
            }
        });
        return maVue;
    }

    public void addListener(IAdapterListener aListener) {
        iAdapterListener = aListener;
    }
}