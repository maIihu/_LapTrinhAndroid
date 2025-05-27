package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHoTen;
    private EditText editTextNamSinh;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHoTen = findViewById(R.id.editTextHoTen);
        editTextNamSinh = findViewById(R.id.editTextNamSinh);
        button = findViewById(R.id.button);


        Intent intent = getIntent();
        if (intent.hasExtra("nam_sinh")) {
            int namSinh = intent.getIntExtra("nam_sinh", -1);
            if (namSinh != -1) {
                editTextNamSinh.setText(String.valueOf(namSinh));
            }
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoTen = editTextHoTen.getText().toString();

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("ho_ten", hoTen);
                startActivity(intent); 
            }
        });
    }
}
