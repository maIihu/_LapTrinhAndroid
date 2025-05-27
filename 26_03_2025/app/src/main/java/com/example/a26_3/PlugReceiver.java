package com.example.a26_3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class PlugReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) {
            Toast.makeText(context, "Plug in", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Plug out", Toast.LENGTH_SHORT).show();
        }
    }
}
