package com.dsv.td1.si3_ihm_aliments.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public interface IConsumerAdapterListener {

    void onClickProduct(int position);

    void onButtonShowPopupWindowClick(View view, Product product, Producer producer);

    void onSettingsClicked();
    void onSubmitSettingsClicked(Consumer consumer, Bundle bundle);

    void onPictureLoad(Bitmap bitmap );
    Bitmap getPictureToSave();

//    void onButtonClicked(int action);
}
