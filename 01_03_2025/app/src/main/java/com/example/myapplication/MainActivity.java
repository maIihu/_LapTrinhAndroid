package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextSoA;
    private EditText editTextSoB;
    private Button button;
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    if(o.getResultCode() == 33){
                        Intent intent = o.getData();
                        int t = intent.getIntExtra("Tong", 1);
                        Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextSoA = (EditText) findViewById(R.id.editTextSoA);
        editTextSoB = (EditText) findViewById(R.id.editTextSoB);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLy();
            }
        });
    }

    private void XuLy(){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        int a = Integer.parseInt(editTextSoA.getText().toString());
        int b = Integer.parseInt(editTextSoB.getText().toString());
        intent.putExtra("SoA", a);
        intent.putExtra("SoB", b);
        //startActivityForResult(intent, 99);
    }



 //   @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 99 && resultCode == 33){
//            int t = data.getIntExtra("Tong", 1);
//            Toast.makeText(MainActivity.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
//        }
//    }
}