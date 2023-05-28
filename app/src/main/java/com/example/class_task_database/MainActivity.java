package com.example.class_task_database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    TextView viewdata;
    Button login, view, update , delete, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        view = findViewById(R.id.view);
        update = findViewById(R.id.Update);
        delete = findViewById(R.id.Delete);
        search = findViewById(R.id.Search);
        viewdata = findViewById(R.id.viewdata);

        SQLite db = new SQLite(MainActivity.this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long check = db.SaveData(username.getText().toString(), password.getText().toString());
                if(check>=0){
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data not save", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userData = db.ViewData();
                viewdata.setText(userData);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean check = db.UpdateData(username.getText().toString(), password.getText().toString());
                if (check)
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
            }

        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean check= db.Delete(username.getText().toString());
                if (check)
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=  db.Search(username.getText().toString());
                viewdata.setText(name);
            }
        });


    }
}