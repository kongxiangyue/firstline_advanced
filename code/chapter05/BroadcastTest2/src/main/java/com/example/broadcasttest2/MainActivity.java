package com.example.broadcasttest2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

       @Override
       public void onReceive(Context context, Intent intent) {
           Toast.makeText(MainActivity.this
                   , "network changes"
                   , Toast.LENGTH_SHORT).show();

            //以下改进代码
            //ConnectivityManager connectionManager = (ConnectivityManager)
            //        getSystemService(Context.CONNECTIVITY_SERVICE);
            //NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            //if (networkInfo != null && networkInfo.isAvailable()) {
            //    Toast.makeText(context, "network is available",
            //            Toast.LENGTH_SHORT).show();
            //} else {
            //    Toast.makeText(context, "network is unavailable",
            //            Toast.LENGTH_SHORT).show();
            //}
       }

    }

}
