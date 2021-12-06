package net.freedynamicdns.samarths.curlnotification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startService(new Intent(getApplicationContext(), ServerRunnerService.class));
       sendBroadcast(new Intent(this, CurlNotificationBroadcastReceiver.class).setAction("StartCurlNotification"));
    }
}