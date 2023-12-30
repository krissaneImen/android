package com.example.speciset;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AjoutActivity extends AppCompatActivity {

    private EditText ED1, ED2, ED3, ED4;
    private Button ajout;
    private DataBaseHelper base;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout);
        ED1 = findViewById(R.id.name);
        ED2 = findViewById(R.id.lastname);
        ED3 = findViewById(R.id.email);
        ED4 = findViewById(R.id.password);
        ajout = findViewById(R.id.add);
        base = new DataBaseHelper(AjoutActivity.this);
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nom = ED1.getText().toString();
                    String prenom = ED2.getText().toString();
                    String email = ED3.getText().toString();
                    String password = ED4.getText().toString();

                    if (!nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                        user user = new user(nom, prenom, email, password);
                        base.insertion(user);
                        Toast.makeText(AjoutActivity.this, "Insertion réussie", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AjoutActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(AjoutActivity.this, "Veuillez entrer un numéro de carte valide", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
