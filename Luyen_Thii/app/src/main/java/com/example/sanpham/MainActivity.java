package com.example.sanpham;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextSearch;
    ListView listView;
    Button btnAdd;
    ArrayList<SanPham> sanPhams;
    SanPhamAdapter sanPhamAdapter;
    SqlLite sqlLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextSearch = findViewById(R.id.editTxtSearch);
        listView = findViewById(R.id.lstView1);
        btnAdd = findViewById(R.id.btnAdd);
        sqlLite = new SqlLite(this);

        sanPhams = sqlLite.getList();
        sanPhamAdapter = new SanPhamAdapter(MainActivity.this, R.layout.san_pham_item, sanPhams);
        listView.setAdapter(sanPhamAdapter);

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });

    }
}