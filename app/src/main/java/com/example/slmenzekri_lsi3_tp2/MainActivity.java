package com.example.slmenzekri_lsi3_tp2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBarAge;
    SeekBar seekBarGlycemie;
    TextView tvAge;
    TextView tvGlycemie;
    RadioGroup radioGroupJeune;
    RadioButton radioOui;
    RadioButton radioNon;
    Button btnCalculer;
    TextView tvResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBarAge = findViewById(R.id.seekBarAge);
        seekBarGlycemie = findViewById(R.id.seekBarGlycemie);
        tvAge = findViewById(R.id.tvAge);
        tvGlycemie = findViewById(R.id.tvGlycemie);
        radioGroupJeune = findViewById(R.id.radioGroupJeune);
        radioOui = findViewById(R.id.radioOui);
        radioNon = findViewById(R.id.radioNon);
        btnCalculer = findViewById(R.id.btnCalculer);
        tvResultat = findViewById(R.id.tvResultat);

        tvAge.setText("Votre age : 1 an");
        tvGlycemie.setText("Valeur mesuree en (mmol/l) : 1.0");

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int age = progress + 1;
                if (age <= 1) {
                    tvAge.setText("Votre age : " + age + " an");
                } else {
                    tvAge.setText("Votre age : " + age + " ans");
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        seekBarGlycemie.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double glycemie = (progress + 10) / 10.0;
                tvGlycemie.setText(String.format("Valeur mesuree en (mmol/l) : %.1f", glycemie));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnCalculer.setOnClickListener(v -> calculerNiveauGlycemie());
    }

    private void calculerNiveauGlycemie() {
        int age = seekBarAge.getProgress() + 1;
        double glycemie = (seekBarGlycemie.getProgress() + 10) / 10.0;
        boolean estJeune = radioOui.isChecked();

        String resultat;
        int couleur;

        if (estJeune) {
            double min;
            double max;
            if (age > 13) {
                min = 5.0;
                max = 7.2;
            } else if (age >= 6) {
                min = 5.0;
                max = 10.0;
            } else {
                min = 5.5;
                max = 10.0;
            }

            if (glycemie < min) {
                resultat = "le niveau de glycemie est trop bas";
                couleur = Color.RED;
            } else if (glycemie > max) {
                resultat = "le niveau de glycemie est trop elevee";
                couleur = Color.RED;
            } else {
                resultat = "le niveau de glycemie est normale";
                couleur = Color.parseColor("#2E7D32");
            }
        } else {
            if (glycemie > 10.5) {
                resultat = "niveau de glycemie est trop elevee";
                couleur = Color.RED;
            } else {
                resultat = "niveau de glycemie est normale";
                couleur = Color.parseColor("#2E7D32");
            }
        }

        tvResultat.setText(resultat);
        tvResultat.setTextColor(couleur);
    }
}
