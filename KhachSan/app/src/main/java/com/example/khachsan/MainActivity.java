package com.example.khachsan;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lstView;
    ArrayList<HoaDon> hoaDons;
    HoaDonAdapter hoaDonAdapter;
    SqlLite sqlLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        lstView = findViewById(R.id.lstView);

        sqlLite = new SqlLite(this);
        hoaDons = sqlLite.getList();

        hoaDonAdapter = new HoaDonAdapter(MainActivity.this, R.layout.hoa_don_item, hoaDons);
        lstView.setAdapter(hoaDonAdapter);


    }
}