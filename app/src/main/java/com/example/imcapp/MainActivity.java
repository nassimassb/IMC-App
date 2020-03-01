package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goImc (View view)
    {
        startActivity(new Intent(this, ImcForm.class));
    }

    public void goTutorial(View view)
    {
        startActivity(new Intent(this,Tuto1.class));
    }
}
