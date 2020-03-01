package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ImcForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcform);
    }

    public void calculate(View view)
    {
        EditText textWeight = findViewById(R.id.weight);
        String weight = textWeight.getText().toString();

        EditText textHeight = findViewById(R.id.height);
        String height = textHeight.getText().toString();

        Intent intent = new Intent(this,ImcResult.class);
        intent.putExtra("EXTRA_Weight", weight);
        intent.putExtra("EXTRA_Height",height);
        startActivity(intent);

    }
}
