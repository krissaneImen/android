package com.example.speciset;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GMActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gm);

        // TODO: Ajoutez le code spécifique à l'activité Info ici
        Toast.makeText(this, "Département GM", Toast.LENGTH_SHORT).show();
    }
}