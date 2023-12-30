package com.example.speciset;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class signup extends PreferenceActivity {
    public static DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.signup);
        db = new DataBaseHelper(signup.this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(signup.this);
        Preference button = findPreference(getString(R.string.btnsignup));

        button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                String name = prefs.getString("name", "");
                String lastname = prefs.getString("lastname", "");
                String email = prefs.getString("email", "");
                String password = prefs.getString("password", "");
                if (name.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signup.this, "Empty field!", Toast.LENGTH_LONG).show();
                } else {
                    new AlertDialog.Builder(signup.this)
                            .setTitle("Sign up")
                            .setMessage("Successfully signed up")
                            .setCancelable(true)
                            .show();

                    user newUser = new user(name, lastname, email, password);
                    long row = db.insertion(newUser);

                    if (row != -1) {
                        Toast.makeText(signup.this, "Success", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(signup.this, login.class);
                        startActivity(i);
                    }
                }
                return true;
            }
        });

        Preference alreadyHaveAccountButton = findPreference("btnAlreadyHaveAccount");
        alreadyHaveAccountButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Rediriger vers la page de connexion
                Intent loginIntent = new Intent(signup.this, login.class);
                startActivity(loginIntent);
                return true;
            }
        });
    }
}
