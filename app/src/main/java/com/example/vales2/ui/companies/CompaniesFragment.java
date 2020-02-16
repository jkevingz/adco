package com.example.vales2.ui.companies;

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

public class CompaniesFragment extends Fragment {

    private CompaniesViewModel companiesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        companiesViewModel =
                ViewModelProviders.of(this).get(CompaniesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_companies, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        companiesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}