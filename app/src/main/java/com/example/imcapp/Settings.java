package com.example.imcapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Switch darkModeToggle;

        sharedPref = new SharedPref(this);

        //set dark theme that we configured
        setTheme(sharedPref.loadNightMode()? R.style.darkTheme: R.style.lightTheme);
        //load selected language
        sharedPref.loadLocale(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //switch between dark and light mode
        darkModeToggle = findViewById(R.id.darkModeToggle);

        if(sharedPref.loadNightMode()){
            darkModeToggle.setChecked(true);
        }
        darkModeToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                sharedPref.setNightMode(true);
            }else{
                sharedPref.setNightMode(false);
            }
            recreate();
        });

        //change language
        Button changeLang = findViewById(R.id.btnChangeLanguage);
        changeLang.setOnClickListener(v -> {
            sharedPref.btnAnimation(v);
            showChangeLanguageDialog();
        });
    }

    private void showChangeLanguageDialog(){
        //Array of languages to display in alert dialog
        final String[] listOfLang = {"Français", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings.this,sharedPref.loadNightMode()? R.style.Theme_AppCompat_DayNight_Dialog: R.style.Theme_AppCompat_Light_Dialog);
        mBuilder.setSingleChoiceItems(listOfLang, -1, (dialog, i) -> {
            if (i == 0){
                //set french
                sharedPref.setLocale("fr", getApplicationContext());
                recreate();
            }
            if (i == 1){
                //set english
                sharedPref.setLocale("en", getApplicationContext());
                recreate();
            }
            //dismiss dialog when language selected
            dialog.dismiss();
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    public void backBtn(View view){
        sharedPref.btnAnimation(view);
        goBack();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }

    public void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}