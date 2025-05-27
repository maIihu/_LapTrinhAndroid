package com.example.myunservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class MyServiceBound extends Service {
    private LocalBinder localBinder = new LocalBinder();
    public MyServiceBound() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service Bound", Toast.LENGTH_SHORT).show();
        return localBinder;
    }
    public int getRandomNumber(){
        return (int)(Math.random()*100);
    }
    class LocalBinder extends Binder {
        MyServiceBound getService() {
            return MyServiceBound.this;
        }
    }
}