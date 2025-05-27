package com.example.myunservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private int serviceCounter = 0;
    private Looper serviceLooper;
    private ServiceHandler serviceHandler;

    public MyService() {
    }

    /// onCreate() is called when the service is created.
    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);

        thread.start();
        serviceLooper = thread.getLooper();

        serviceHandler = new ServiceHandler(serviceLooper);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent!=null){
            serviceCounter = intent.getIntExtra(MainActivity.EXTRA_KEY, 0);
            serviceCounter++;
        }
        Toast.makeText(this, "Service Counter: " + serviceCounter, Toast.LENGTH_SHORT).show();
        Message message = serviceHandler.obtainMessage();
        message.arg1 = startId;
        serviceHandler.sendMessage(message);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class ServiceHandler extends Handler {
        ServiceHandler(Looper looper) {
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            try {
                Thread.sleep(5000);
                // goi api ,tai file , ....
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
    }


}