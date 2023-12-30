package com.example.speciset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private EditText nomEditText, prenomEditText, emailEditText, passwordEditText;
    private Button modifierButton;
    private DataBaseHelper dataBaseHelper;
    private int userId; // Vous devez obtenir cet ID lors de la sélection de l'élément dans la liste

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier);

        nomEditText = findViewById(R.id.nom);
        prenomEditText = findViewById(R.id.prenom);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        modifierButton = findViewById(R.id.modifierButton);

        dataBaseHelper = new DataBaseHelper(EditActivity.this);

        // Récupérer l'ID de l'utilisateur à partir de l'intent (à adapter en fonction de la façon dont vous passez l'ID)
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getIntExtra("USER_ID", -1);
            if (userId == -1) {
                // Gérer l'erreur, l'ID n'a pas été fourni correctement
                finish();
            } else {
                // Charger les données de l'utilisateur à partir de la base de données et les afficher dans les EditText
                loadUserData(userId);
            }
        }

        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les nouvelles valeurs des champs
                String nom = nomEditText.getText().toString();
                String prenom = prenomEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Mettre à jour l'utilisateur dans la base de données
                updateUser(userId, nom, prenom, email, password);

                // Afficher un message de succès
                Toast.makeText(EditActivity.this, "Utilisateur mis à jour avec succès", Toast.LENGTH_SHORT).show();

                // Finir l'activité
                finish();
            }
        });
    }

    private void loadUserData(int userId) {
        // Utilisez votre méthode appropriée dans la base de données pour obtenir les données de l'utilisateur par ID
        user userData = dataBaseHelper.getUserById(userId);

        // Affichez les données dans les EditText appropriés
        nomEditText.setText(userData.getNom());
        prenomEditText.setText(userData.getPrenom());
        emailEditText.setText(userData.getEmail());
        passwordEditText.setText(userData.getPassword());
    }

    private void updateUser(int userId, String nom, String prenom, String email, String password) {
        // Utilisez votre méthode appropriée dans la base de données pour mettre à jour l'utilisateur par ID
        user updatedUser = new user(userId, nom, prenom, email, password);
        dataBaseHelper.updateUser(updatedUser);
    }
}
