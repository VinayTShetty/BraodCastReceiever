package com.education.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class MyDemo extends BroadcastReceiver {
    private Callback callback;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiState) {
                case WifiManager.WIFI_STATE_ENABLED:
                    // WiFi is turned on
                    callback.onWifiStateChanged(true);
                    Toast.makeText(context, "WiFi turned on", Toast.LENGTH_SHORT).show();
                    break;

                case WifiManager.WIFI_STATE_DISABLED:
                    // WiFi is turned off
                    callback.onWifiStateChanged(false);
                    Toast.makeText(context, "WiFi turned off", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    public interface Callback {
        void onWifiStateChanged(boolean isWifiEnabled);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
