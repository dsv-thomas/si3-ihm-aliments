package com.dsv.td1.si3_ihm_aliments.ui_consumer.map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModelConsumer extends ViewModel {

    private MutableLiveData<String> mText;

    public MapViewModelConsumer() {
        mText = new MutableLiveData<>();
        mText.setValue("This is map fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}