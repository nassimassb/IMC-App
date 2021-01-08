package com.example.imcapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class SharedPref {

    SharedPreferences mySharedPref;

    public SharedPref(Context context){

        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    //save the dark mode state
    public void setNightMode(Boolean state){

        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("darkMode",state);
        editor.commit();
    }

    //load the dark mode state
    public Boolean loadNightMode(){
        Boolean state = mySharedPref.getBoolean("darkMode", false);
        return state;
    }

    public static void btnAnimation(View view){
        //little animation when button is clicked
        AlphaAnimation animation1 = new AlphaAnimation(0.2f, 1.0f);
        animation1.setDuration(500);
        view.startAnimation(animation1);
    }

    //save the sound state
//    public void setSound(Boolean state){
//
//        SharedPreferences.Editor editor = mySharedPref.edit();
//        editor.putBoolean("sound",state);
//        editor.commit();
//    }
//
//    //get the sound preference
//    public Boolean getSound(){
//        Boolean soundState = mySharedPref.getBoolean("sound", true);
//        return soundState;
//    }

    //set saved language
    public void setLocale(String lang, Context context) {
        java.util.Locale locale = new java.util.Locale(lang);

        java.util.Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    //load saved language
    public void loadLocale(Context context){
        SharedPreferences pref = context.getSharedPreferences("settings", Activity.MODE_PRIVATE);
        String language = pref.getString("My lang", java.util.Locale.getDefault().getLanguage());
        setLocale(language, context);
    }
}
