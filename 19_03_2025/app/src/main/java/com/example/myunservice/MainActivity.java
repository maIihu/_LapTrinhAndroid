package com.example.myunservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    public int counter = 0;
    public static final String EXTRA_KEY = "EXTRA_KEY";
    private boolean isBound = false;
    private MyServiceBound myServiceBound = new MyServiceBound();
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBound = true;
            MyServiceBound.LocalBinder binder = (MyServiceBound.LocalBinder) iBinder;
            myServiceBound = binder.getService();
            counter = myServiceBound.getRandomNumber();
            Toast.makeText(MainActivity.this, "Counter: " + counter, Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

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
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBound) {
                    Toast.makeText(MainActivity.this, "Service chưa được kết nối!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (textView != null) {
                    int randomNumber = myServiceBound.getRandomNumber();
                    textView.setText(String.valueOf(randomNumber));
                } else {
                    Toast.makeText(MainActivity.this, "TextView chưa được khởi tạo!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(MainActivity.this, MyServiceBound.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}