package com.example.vales2.ui.companies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompaniesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CompaniesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Companies fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}