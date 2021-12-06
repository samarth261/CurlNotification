package net.freedynamicdns.samarths.curlnotification;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.Toast;

public class ServerRunnerService extends Service {
    public ServerRunnerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        new Handler(thread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                Handler uihandle = new Handler(getMainLooper());
                NotificationServer server = new NotificationServer(uihandle, getApplicationContext());
                server.StartListening();
            }
        });

        return Service.START_STICKY;
    }

    // Send a broadcast to start the service again.
    @Override
    public void onDestroy()
    {
        // super.onDestroy();
        // sendBroadcast(new Intent(this, CurlNotificationBroadcastReceiver.class).setAction(Constants.Actions.START_CURL_NOTIFICATION_SERVICE));
        // Toast.makeText(this, "Sending broadcast to start service again", Toast.LENGTH_LONG).show();
    }

}