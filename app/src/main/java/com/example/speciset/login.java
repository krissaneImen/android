package com.example.speciset;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class login extends PreferenceActivity {

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.login);

        dbHelper = new DataBaseHelper(this);

        Preference btnLogin = findPreference("btnlogin");
        btnLogin.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(login.this);
                String savedEmail = preferences.getString("email", "");
                String savedPassword = preferences.getString("password", "");

                EditTextPreference emailEditTextPreference = (EditTextPreference) findPreference("email");
                EditTextPreference passwordEditTextPreference = (EditTextPreference) findPreference("password");

                String enteredEmail = emailEditTextPreference.getText();
                String enteredPassword = passwordEditTextPreference.getText();

                // Vérifier si l'utilisateur est l'administrateur
                if (enteredEmail.equals("admin@iset.tn") && enteredPassword.equals("admin")) {
                    Toast.makeText(login.this, "Connexion réussie (administrateur)", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, AdminActivity.class);
                    startActivity(intent);
                    return true;
                }

                // Vérifier si l'utilisateur est dans la base de données SQLite
                if (isValidUserInDatabase(enteredEmail, enteredPassword)) {
                    Toast.makeText(login.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, SpecActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    Toast.makeText(login.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }

    private boolean isValidUserInDatabase(String enteredEmail, String enteredPassword) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        boolean userExists = false;

        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME + " WHERE " +
                        DataBaseHelper.COL_4 + "=? AND " + DataBaseHelper.COL_5 + "=?",
                new String[]{enteredEmail, enteredPassword});

        if (cursor.moveToFirst()) {
            userExists = true;
        }

        cursor.close();
        db.close();

        return userExists;
    }
}
