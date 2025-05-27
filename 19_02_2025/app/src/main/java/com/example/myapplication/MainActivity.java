package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewSinhvien;
    EditText editTextHoten, editTextNamsinh;
    Button buttonAdd, buttonUpdate;
    int val;
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

        listViewSinhvien = (ListView) findViewById(R.id.listViewSinhvien);

        ArrayList<SinhVien> arrayListSinhvien = new ArrayList<SinhVien>();
        arrayListSinhvien.add(new SinhVien("Nguyen Van A", 1981));
        arrayListSinhvien.add(new SinhVien("Pham Van C", 2002));
        arrayListSinhvien.add(new SinhVien("Tran thi D", 1998));

        SinhVienAdapter adapter = new SinhVienAdapter(MainActivity.this, R.layout.sv_item, arrayListSinhvien);
        listViewSinhvien.setAdapter(adapter);
        listViewSinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,
                        arrayListSinhvien.ge

                        t(position).HoTen+ " "+
                        String.valueOf(arrayListSinhvien.get(position).NamSinh),
                        Toast.LENGTH_SHORT).show();
                val = position;
                editTextHoten.setText(arrayListSinhvien.get(position).HoTen);
                editTextNamsinh.setText(String.valueOf(arrayListSinhvien.get(position).NamSinh));
            }
        });
        editTextHoten = (EditText) findViewById(R.id.editTextHoten);
        editTextNamsinh = (EditText) findViewById(R.id.editTextnamsinh);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextHoten.getText().toString().equals("")&&
                !editTextNamsinh.getText().toString().equals("")){
                    String ht = editTextHoten.getText().toString();
                    String ns = editTextNamsinh.getText().toString();
                    arrayListSinhvien.add(new SinhVien(ht, Integer.parseInt(ns)));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arrayListSinhvien.set(val, new SinhVien(editTextHoten.getText().toString(),
                        Integer.parseInt(editTextNamsinh.getText().toString())));
                adapter.notifyDataSetChanged();
            }
        });
    }
}