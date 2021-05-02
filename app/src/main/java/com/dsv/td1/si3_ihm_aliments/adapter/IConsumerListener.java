package com.dsv.td1.si3_ihm_aliments.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;

import com.dsv.td1.si3_ihm_aliments.consumer.Consumer;
import com.dsv.td1.si3_ihm_aliments.consumer.Reservation;
import com.dsv.td1.si3_ihm_aliments.producer.Producer;
import com.dsv.td1.si3_ihm_aliments.product.Product;

public interface IConsumerListener {
    void onSettingsClicked();
    void onSubmitSettingsClicked(Consumer consumer, Bundle bundle);

    void onPictureLoad(Bitmap bitmap );
    Bitmap getPictureToSave();
}
