package com.example.imcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;

public class ImcResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcresult);

        Intent intent = getIntent();

        double result = 0;
        String poids = intent.getStringExtra("EXTRA_Weight");
        String taille = intent.getStringExtra("EXTRA_Height");
        TextView textView = findViewById(R.id.textViewResult);
        DecimalFormat df2 = new DecimalFormat("#.###");
        TextView tvResult = findViewById(R.id.tvResult);

        if(!(poids.matches("") || taille.matches("")))
        {
            try {
                double dPoids = DecimalFormat.getNumberInstance().parse(poids).doubleValue();
                double dTaille = DecimalFormat.getNumberInstance().parse(taille).doubleValue();

                dTaille = dTaille/100;
                result = dPoids / (Math.pow(dTaille,2));
                textView.setText(poids + " / " + dTaille + "² = " + Math.floor(result * 1e2) / 1e2 + "\n\n Selon la classification de l'OMS, l'interprétation de votre IMC ("+Math.floor(result * 1e2) / 1e2+"kg/m²) est :");

                if(result < 16)
                {
                    tvResult.setText("Anorexie ou dénutrition");
                }
                else if(result > 16.5 && result < 18.5)
                {
                    tvResult.setText("Maigreur");
                }
                else if(result > 18.5 && result < 25)
                {
                    tvResult.setText("Corpulence normale");
                }
                else if(result > 25 && result < 30)
                {
                    tvResult.setText("Surpoids");
                }
                else if(result > 30 && result < 35)
                {
                    tvResult.setText("Obésité modérée (Classe 1)");
                }
                else if(result > 35 && result < 40)
                {
                    tvResult.setText("Obésité élevé (Classe 2)");
                }
                else
                {
                    tvResult.setText("Obésite morbide ou massive");
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        else
        {
            textView.setText("Aucune info disponible");
        }
    }
}
