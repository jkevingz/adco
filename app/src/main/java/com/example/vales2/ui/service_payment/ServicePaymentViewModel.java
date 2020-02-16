package com.example.vales2.ui.service_payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ServicePaymentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ServicePaymentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is service payment fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}