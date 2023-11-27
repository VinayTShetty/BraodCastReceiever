package com.education.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyDemo.Callback {
    MyDemo myDemo = new MyDemo();
    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView=(TextView)findViewById(R.id.status_tv);
        myDemo.setCallback(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(myDemo, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myDemo);
    }


    @Override
    public void onWifiStateChanged(boolean isWifiEnabled) {
        if(isWifiEnabled){
            myTextView.setText("Wifi ON");
        }else {
            myTextView.setText("Wifi OFF");
        }
    }
}