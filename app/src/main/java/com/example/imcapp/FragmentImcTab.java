package com.example.imcapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class FragmentImcTab extends Fragment {

    boolean advanced = false;

    public FragmentImcTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imc_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageButton btnAdvancedDown = getView().findViewById(R.id.btnAdvancedDown);
        ImageButton btnAdvancedUp = getView().findViewById(R.id.btnAdvancedUp);
        LinearLayout layoutAdvanced = getView().findViewById(R.id.layoutAdvanced);

        btnAdvancedDown.setOnClickListener(v -> {
            layoutAdvanced.setVisibility(View.VISIBLE);
            btnAdvancedUp.setVisibility(View.VISIBLE);
            advanced = true;
        });

        btnAdvancedUp.setOnClickListener(v -> {
            layoutAdvanced.setVisibility(View.GONE);
            btnAdvancedUp.setVisibility(View.GONE);
            advanced = false;
        });
    }
}