package com.example.imcapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class FragmentInspiration extends Fragment {

    public FragmentInspiration() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inspiration_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        List<Integer> frPics = new ArrayList<>();
        List<Integer> enPics = new ArrayList<>();
        LinearLayout inspirationLayout = getView().findViewById(R.id.inspirationLayout);

        if(java.util.Locale.getDefault().getLanguage() == "fr"){
            frPics.add(R.drawable.fr_curl);
            frPics.add(R.drawable.fr_tirage);

            if (frPics.size() != 0){
                for (int i = 0; i < frPics.size(); i++) {
                    ImageView imageView = new ImageView(getActivity());

                    imageView.setImageResource(frPics.get(i));
                    //setting image position
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(970,
                            1100));
                    //adding view to layout
                    inspirationLayout.addView(imageView);
                }
            }

        }else{
            if(enPics.size() != 0){
                for (int i = 0; i < enPics.size(); i++) {
                    ImageView imageView = new ImageView(getActivity());

                    imageView.setImageResource(frPics.get(i));
                    //setting image position
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(970,
                            1100));
                    //adding view to layout
                    inspirationLayout.addView(imageView);
                }
            }
        }
    }
}