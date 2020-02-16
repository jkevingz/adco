package com.example.vales2.ui.adco_tutorials;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdcoTutorialsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdcoTutorialsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ADCO tutorials fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}