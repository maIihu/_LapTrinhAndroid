package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ManHinh2 extends AppCompatActivity {

    private Button buttonBack;
    private TextView textView, textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh2);

        Intent intent1 = getIntent();
        String ht = intent1.getStringExtra("hoten");
        int ns = intent1.getIntExtra("namsinh", 0);

        buttonBack = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.textView2);
        textView2 = (TextView) findViewById(R.id.textView3);

        textView.setText(ht);
        textView2.setText(String.valueOf(ns));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinh2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}