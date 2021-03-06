package com.example.vales2.ui.contact_us;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.vales2.R;

public class ContactUsFragment extends Fragment {

    private ContactUsViewsModel contactUsViewsModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactUsViewsModel =
                ViewModelProviders.of(this).get(ContactUsViewsModel.class);
        View root = inflater.inflate(R.layout.fragment_contact_us, container, false);
        final TextView textView = root.findViewById(R.id.text_contact_us);
        contactUsViewsModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}