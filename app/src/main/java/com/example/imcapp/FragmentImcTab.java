package com.example.imcapp;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class FragmentImcTab extends Fragment {

    SharedPref sharedPref;
    private InterstitialAd mInterstitialAd;
    boolean advanced = false;

    public FragmentImcTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedPref = new SharedPref(getActivity());

        //init interstitial ad
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-6646767279000716/8043273256");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_imc_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        CheckBox checkboxMe = getView().findViewById(R.id.checkBox); //check if we need to save to "my profile" or "others"
        Button btnProfile = getView().findViewById(R.id.btnProfile);
        Button btnOthers = getView().findViewById(R.id.btnOthers);
        Button btnBuy = getView().findViewById(R.id.btnBuy);
        Button btnConfirm = getView().findViewById(R.id.btnConfirm);

        EditText etFullName = getView().findViewById(R.id.etFullName);

        ImageButton btnAdvancedDown = getView().findViewById(R.id.btnAdvancedDown);
        ImageButton btnAdvancedUp = getView().findViewById(R.id.btnAdvancedUp);

        LinearLayout layoutAdvanced = getView().findViewById(R.id.layoutAdvanced);

        btnProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), MyProfile.class)));
        btnOthers.setOnClickListener(v -> startActivity(new Intent(getActivity(), Others.class)));
        ScrollView imcScrollView = (ScrollView) getView().findViewById(R.id.scrollImc);

        btnBuy.setOnClickListener(v -> {
            sharedPref.btnAnimation(v);

            final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
            final View mView = getLayoutInflater().inflate(R.layout.popup_webview, null);
            WebView myWebView = (WebView) mView.findViewById(R.id.myWebView);
            ImageButton btnCloseWebView = (ImageButton) mView.findViewById(R.id.btnCloseWeb);

            mBuilder.setView(mView);
            final AlertDialog mDialog = mBuilder.create();
            mDialog.show();

            try {
                String link;

                //switch between amazon.com and amazon.fr depending on the language
                if(sharedPref.getLanguage(getActivity()) == "fr"){
                    link = "https://www.amazon.fr/s?k=balance+impedancemetre&__mk_fr_FR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss_1";
                }else{
                    link = "https://www.amazon.com/s?k=body+composition+scale&ref=nb_sb_noss";
                }
                imcScrollView.fullScroll(ScrollView.FOCUS_UP); //scroll to top
                WebSettings settings = myWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                myWebView.loadUrl(link);
                myWebView.setVisibility(View.VISIBLE);

            } catch (ActivityNotFoundException e) {
                Toast.makeText(getActivity(), "No application can handle this request."
                        + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            btnCloseWebView.setOnClickListener(v1 -> mDialog.dismiss());
        });

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

        btnConfirm.setOnClickListener(v -> {
            //show Ad every 4 click
            if(sharedPref.adShown()){
                //create new interstitial ad
                AdRequest adRequestInterstitial = new AdRequest.Builder().build();
                mInterstitialAd.loadAd(adRequestInterstitial);

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }
            }
            //switch between true and false
            sharedPref.setAdShown(!sharedPref.adShown());

            if (!advanced){
                //TODO: save the info in MyProfile or Others
            }
        });
    }
}