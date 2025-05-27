package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class Activity2 extends AppCompatActivity {

    private TextView textView;
    private ListView listViewSV;
    private Button button;
    private ArrayList<SinhVien> listSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2); // Sửa lỗi thiếu setContentView()

        listViewSV = findViewById(R.id.ListViewSinhVien);
        button = findViewById(R.id.button2);

        listSV = new ArrayList<>();
        listSV.add(new SinhVien("Ga Duc Vinh", 2004));
        listSV.add(new SinhVien("TV Minh", 2024));
        listSV.add(new SinhVien("LeX BA", 2004));

        Intent intent = getIntent();
        String ht = intent.getStringExtra("ho_ten"); // Sửa key

        // Tìm sinh viên theo họ tên
        SinhVien find_sv = null;
        for (SinhVien x : listSV) {
            if (Objects.equals(x.getHoTen(), ht)) {
                find_sv = x;
                break;
            }
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (SinhVien sv : listSV) {
            adapter.add(sv.getHoTen() + " - " + sv.getNamSinh());
        }
        listViewSV.setAdapter(adapter);


        SinhVien finalFind_sv = find_sv;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalFind_sv != null) {
                    Intent intent1 = new Intent(Activity2.this, MainActivity.class);
                    intent1.putExtra("nam_sinh", finalFind_sv.getNamSinh());
                    startActivity(intent1);
                }
            }
        });
    }
}
