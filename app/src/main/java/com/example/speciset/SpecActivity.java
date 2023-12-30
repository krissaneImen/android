package com.example.speciset;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SpecActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spec);
        ListView listview = findViewById(R.id.listview);
        spec info = new spec("Développement système d'Information", 100);
        spec cf = new spec("Comptabilité Finance", 180);
        spec gter = new spec("Génie thermique d'énergie renouvelable", 300);
        spec gm = new spec("Génie Mécanique", 99);

        spec[] specs = new spec[]{info, cf, gter, gm};

        ArrayAdapter<spec> arrayAdapter = new ArrayAdapter<spec>(this, android.R.layout.simple_list_item_1, specs);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                spec selectedItem = (spec) adapterView.getItemAtPosition(position);

                Intent intent;
                if ("Développement système d'Information".equals(selectedItem.getName())) {
                    intent = new Intent(SpecActivity.this, InfoActivity.class);
                } else if ("Comptabilité Finance".equals(selectedItem.getName())) {
                    intent = new Intent(SpecActivity.this, CFAActivity.class);
                } else if ("Génie thermique d'énergie renouvelable".equals(selectedItem.getName())) {
                    intent = new Intent(SpecActivity.this, GterActivity.class);
                } else if ("Génie Mécanique".equals(selectedItem.getName())) {
                    intent = new Intent(SpecActivity.this, GMActivity.class);
                } else {
                    intent = null;
                }

                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(SpecActivity.this, "Activity not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
