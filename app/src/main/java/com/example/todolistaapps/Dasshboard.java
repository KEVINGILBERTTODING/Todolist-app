package com.example.todolistaapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Dasshboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasshboard);


        ImageButton btn_todo = (ImageButton) findViewById(R.id.btn_todo);
        btn_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dasshboard.this, MainActivity.class);
                startActivity(intent);

            }
        });

        ImageButton btn = (ImageButton) findViewById(R.id.imageButton2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dasshboard.this, com.example.todolistaapps.Profile.class);
                startActivity(intent);
            }
        });

    }
}