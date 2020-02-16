package com.example.vales2.ui.adco_tutorials;

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

public class AdcoTutorialsFragment extends Fragment {

    private AdcoTutorialsViewModel adcoTutorialsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adcoTutorialsViewModel =
                ViewModelProviders.of(this).get(AdcoTutorialsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_adco_tutorials, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        adcoTutorialsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}