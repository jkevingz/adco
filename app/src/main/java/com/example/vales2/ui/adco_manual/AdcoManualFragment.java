package com.example.vales2.ui.adco_manual;

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

public class AdcoManualFragment extends Fragment {

    private AdcoManualViewModel adcoManualViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        adcoManualViewModel =
                ViewModelProviders.of(this).get(AdcoManualViewModel.class);
        View root = inflater.inflate(R.layout.fragment_adco_manual, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        adcoManualViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}