package com.example.speciset;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioAdmin;
    private RadioButton radioVisiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.radiogroup);
        radioAdmin = findViewById(R.id.radio_admin);
        radioVisiteur = findViewById(R.id.radio_visiteur);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioAdmin.getId()) {
                    Intent loginIntent = new Intent(MainActivity.this, login.class);
                    startActivity(loginIntent);
                } else  {

                    Intent loginIntent = new Intent(MainActivity.this, signup.class);
                    startActivity(loginIntent);
                }
            }
        });
    }

}
