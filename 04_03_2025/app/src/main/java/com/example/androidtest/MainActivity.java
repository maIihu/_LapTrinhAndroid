package com.example.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtBirth;
    Button btnSubmit;
    ListView sinhVienList;
    ArrayList<SinhVien> sinhViens;
    SinhVienAdapter sinhVienAdapter;
            ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if (o.getResultCode() == -1){
                        sinhViens.clear();
                        SinhVien sinhVien = (SinhVien) o.getData().getSerializableExtra("sinhvien");
                        sinhViens.add(sinhVien);
                        sinhVienAdapter.notifyDataSetChanged();
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sinhVienList = findViewById(R.id.sinhVienList);
        sinhViens = new ArrayList<>();
        sinhViens.add(new SinhVien("Trương Văn Minh", 2004));
        sinhViens.add(new SinhVien("Nguyễn Mạnh Hùng", 2005));

        sinhVienAdapter = new SinhVienAdapter(MainActivity.this, R.layout.sv_item, sinhViens);
        sinhVienList.setAdapter(sinhVienAdapter);

        txtBirth = findViewById(R.id.txtBirth);
        txtName = findViewById(R.id.txtName);
        btnSubmit = findViewById(R.id.submit);

        btnSubmit.setOnClickListener(v -> {
            String name = txtName.getText().toString();
            int birth = Integer.parseInt(txtBirth.getText().toString());

            SinhVien sinhVien = new SinhVien(name, birth);

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("Sinhvien", sinhVien);
            activityResultLauncher.launch(intent);
            //startActivityForResult(intent, 100);
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100){
//            sinhViens.clear();
//            SinhVien sinhVien = (SinhVien) data.getSerializableExtra("sinhvien");
//            sinhViens.add(sinhVien);
//            sinhVienAdapter.notifyDataSetChanged();
//        }
//    }
}