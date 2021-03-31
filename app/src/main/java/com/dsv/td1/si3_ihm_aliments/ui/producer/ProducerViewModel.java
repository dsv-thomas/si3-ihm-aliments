package com.dsv.td1.si3_ihm_aliments.ui.producer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProducerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProducerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}