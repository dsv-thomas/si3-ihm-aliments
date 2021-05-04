package com.dsv.td1.si3_ihm_aliments.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;

public interface IProducerListener {
    void onSettingsClicked();
    void onSubmitSettingsClicked(Producer producer, Bundle bundle);


    void onProfilPictureLoad(Bitmap bitmap);
    void onProductPictureLoad(Bitmap bitmap);

    Bitmap getPictureToSave();

    void onAddProductClicked();

    void onSubmitAddProductClicked(Producer producer, Bundle bundle);

    void onButtonShowPopupAddPickupPointClick(View view);

//    void onButtonClicked(int action);
}
