package com.example.a02_04_2025;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThemBaiHat extends AppCompatActivity {

    private EditText etName;
    private EditText etSinger;
    private EditText etTime;
    private Button btnAdd;

    SqlHelper sqlHelper = new SqlHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_bai_hat);

        etName = findViewById(R.id.editTen);
        etSinger = findViewById(R.id.editTxtSinger);
        etTime = findViewById(R.id.editTxtTime);
        btnAdd = findViewById(R.id.btnThem);

        btnAdd.setOnClickListener(v -> {
            long res = sqlHelper.insertTable(etName.getText().toString(), etSinger.getText().toString(), Float.parseFloat(etTime.getText().toString()));
            if (res == -1){
                Toast.makeText(ThemBaiHat.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(ThemBaiHat.this, "INSERTED", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}