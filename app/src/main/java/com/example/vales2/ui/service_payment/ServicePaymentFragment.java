package com.example.vales2.ui.service_payment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vales2.R;

public class ServicePaymentFragment extends Fragment {

    private ServicePaymentViewModel servicePaymentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        servicePaymentViewModel =
                ViewModelProviders.of(this).get(ServicePaymentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_service_payment, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        servicePaymentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}