package com.example.a02_04_2025;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private Button btn;
    ArrayList<BaiHat>baiHats;
    BaiHatAdapter baiHatAdapter;
    SqlHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewBH);
        btn = findViewById(R.id.btn);

        sqlHelper = new SqlHelper(this);

//        sqlHelper.insertTable("Phút cuối", "Bằng Kiều", 3.27F);
//        sqlHelper.insertTable("Bông hồng thủy tinh", "Bức tường", 4.18F);

        baiHats = sqlHelper.getAllBaiHat();

        baiHatAdapter = new BaiHatAdapter(MainActivity.this, R.layout.bai_hat_item, baiHats);
        listView.setAdapter(baiHatAdapter);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ThemBaiHat.class);
            startActivity(intent);
        });
    }
}