package com.example.sanpham;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    private EditText etName;
    private EditText etCost;
    private Button btnAdd;
    SqlLite sqlHelper = new SqlLite(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        etName = findViewById(R.id.editTen);
        etCost = findViewById(R.id.editGia);
        btnAdd = findViewById(R.id.btnThem);

        btnAdd.setOnClickListener(v -> {
            long res = sqlHelper.insertTable(etName.getText().toString(), Integer.parseInt(etCost.getText().toString()), false);
            if (res == -1){
                Toast.makeText(MainActivity2.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity2.this, "INSERTED", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}