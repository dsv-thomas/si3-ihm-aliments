package com.dsv.td1.si3_ihm_aliments.adapter;

import android.view.View;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public interface IConsumerAdapterListener {

    void onClickProduct(int position);

    void onButtonShowPopupWindowClick(View view, Product product, Producer producer);

//    void onButtonClicked(int action);
}
