package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonNext;
    private EditText editText, editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        buttonNext = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editTextText);
        editText2 = (EditText) findViewById(R.id.editTextText2);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ManHinh2.class);
                String ht = editText.getText().toString();
                int ns = Integer.parseInt(editText2.getText().toString());

                intent.putExtra("hoten", ht);
                intent.putExtra("namsinh", ns);

                startActivity(intent);
            }
        });
    }

}