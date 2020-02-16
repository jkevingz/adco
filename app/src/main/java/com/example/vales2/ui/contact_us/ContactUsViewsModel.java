package com.example.vales2.ui.contact_us;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactUsViewsModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactUsViewsModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contact us fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}