package com.example.vales2.ui.adco_manual;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdcoManualViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdcoManualViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ADCO manual fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}