package com.example.speciset;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private List<user> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        final DataBaseHelper dbHelper = new DataBaseHelper(this);
        userList = dbHelper.getAllusers();

        ArrayAdapter<user> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                user selectedUser = userList.get(position);

                dbHelper.deleteUser(selectedUser.getEmail());

                userList = dbHelper.getAllusers();
                adapter.notifyDataSetChanged();

                Toast.makeText(DeleteActivity.this, "Étudiant supprimé avec succès", Toast.LENGTH_SHORT).show();
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
