package com.example.androidtest;

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

public class SQLActivity extends AppCompatActivity {

    EditText id, name, yearofBirth;
    Button insert;
    SQLHelper sqlHelper = new SQLHelper(this);

    @Override
    protected void onStart() {
        super.onStart();
        sqlHelper.onOpen(sqlHelper.getSqLiteDatabase());
    }

    @Override
    protected void onStop() {
        super.onStop();
        sqlHelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initWidgets();

        insert.setOnClickListener(v -> {
            long result = sqlHelper.Insert(name.getText().toString(),
                    Integer.parseInt(yearofBirth.getText().toString()));
            if (result == -1){
                Toast.makeText(SQLActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(SQLActivity.this, "INSERTED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initWidgets() {
        id = findViewById(R.id.txtId);
        name = findViewById(R.id.txtFullName);
        yearofBirth = findViewById(R.id.txtYearOfBirth);
        insert = findViewById(R.id.insert);
    }
}