package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("Sinhvien");

        TextView name = findViewById(R.id.txtShowName);
        TextView birth = findViewById(R.id.txtShowBirth);


        ArrayList<SinhVien> sinhViens = new ArrayList<>();
        sinhViens.add(new SinhVien("Trương Văn Minh", 2004));
        sinhViens.add(new SinhVien("Nguyễn Mạnh Hùng", 2005));

        name.setText(sinhVien.getName());
        birth.setText(String.valueOf(sinhVien.getDataOfBirth()));
        TextView countdown = findViewById(R.id.txtCountdown);
        for (SinhVien a : sinhViens) {
            if (a.getDataOfBirth() == sinhVien.getDataOfBirth()){
                new Thread(() -> {
                    for(i = 5; i >= 0; i--){
                        countdown.post(() -> countdown.setText("Quay trở lại sau " + i + " giây"));
                        if (i == 0){
                            runOnUiThread(() -> {
                                Intent intent1 = new Intent();
                                intent1.putExtra("sinhvien", a);
                                setResult(Activity.RESULT_OK, intent1);
                                finish();
                            });
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        }
    }
}