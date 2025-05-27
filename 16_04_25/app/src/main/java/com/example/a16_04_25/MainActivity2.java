package com.example.a16_04_25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    EditText txtId;
    EditText txtName;
    EditText txtNumber;
    Button btnBack;
    Button btnAdd;

    SqlLite sqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        txtId = findViewById(R.id.editTxtId);
        txtName = findViewById(R.id.editTxtName);
        txtNumber = findViewById(R.id.editTextNumber);
        btnAdd = findViewById(R.id.btnAdd1);
        btnBack = findViewById(R.id.btnBack);

        sqlLite = new SqlLite(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long res = sqlLite.insertTable(Integer.parseInt(txtId.getText().toString()), txtName.getText().toString(), txtNumber.getText().toString());
                if (res == -1){
                    Toast.makeText(MainActivity2.this, "Loi", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity2.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}