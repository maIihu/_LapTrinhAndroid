package com.example.thuchi3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            Toast.makeText(context, "Sẵn sàng đồng bộ dữ liệu lên cloud", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Mất kết nối nên không thể đồng bộ dữ liệu lên cloud", Toast.LENGTH_SHORT).show();
        }
    }
}

