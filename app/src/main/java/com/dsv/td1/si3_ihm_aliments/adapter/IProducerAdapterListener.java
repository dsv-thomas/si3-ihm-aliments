package com.dsv.td1.si3_ihm_aliments.adapter;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.dsv.td1.si3_ihm_aliments.producer.Producer;

public interface IProducerAdapterListener {

    void onClickProducer(int position);

    void onSubmitSettingsClicked(Producer producer, Bundle bundle);

    void onPictureLoad(Bitmap bitmap );
    Bitmap getPictureToSave();


//    void onButtonClicked(int action);
}
