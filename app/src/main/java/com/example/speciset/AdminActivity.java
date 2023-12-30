package com.example.speciset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        Button addButton = findViewById(R.id.add);
        Button ReadButton = findViewById(R.id.read);
        Button DeleteButton = findViewById(R.id.delete);
        Button editButton = findViewById(R.id.modifier);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ajoutIntent = new Intent(AdminActivity.this, AjoutActivity.class);
                startActivity(ajoutIntent);
            }
        });
        ReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent readIntent = new Intent(AdminActivity.this, UserListActivity.class);
                startActivity(readIntent);
            }
        });
        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  deleteIntent = new Intent(AdminActivity.this,DeleteActivity.class);
                startActivity(deleteIntent);
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editIntent = new Intent(AdminActivity.this, EditActivity.class);
                startActivity(editIntent);
            }
        });

    }
}
