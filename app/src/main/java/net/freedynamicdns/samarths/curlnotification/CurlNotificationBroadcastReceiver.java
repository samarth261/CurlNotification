package net.freedynamicdns.samarths.curlnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CurlNotificationBroadcastReceiver extends BroadcastReceiver {

    Context context_;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        context_ = context;
        Log.d("mylog", "broadcast receiver: " + action);

        if (action == Constants.Actions.START_CURL_NOTIFICATION_SERVICE) {
            StartServer();
        } else if (action == Constants.Actions.STOP_CURL_NOTIFICATION_SERVICE) {
            StopServer();
        }
    }

    private void StartServer() {
        context_.startService(new Intent(context_, ServerRunnerService.class));
    }

    private void StopServer() {
        return;
    }
}