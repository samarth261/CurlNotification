package net.freedynamicdns.samarths.curlnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CurlNotificationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, ServerRunnerService.class));
    }
}