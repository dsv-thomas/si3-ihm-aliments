package com.dsv.td1.si3_ihm_aliments.adapter;

import android.view.View;

import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public interface IAdapterListener {
    int ACTION_CLICK_PRODUCT = 1;
    int ACTION_CLICK_PRODUCER = 2;

    void onClickItemListView(int position, int action);

    void deleteReservation(Reservation reservation);

    void onButtonShowPopupWindowClick(View view, Product product, Producer producer);

}
