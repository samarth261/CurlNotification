package net.freedynamicdns.samarths.curlnotification;

import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationManager {

    public static final String DEFAULT_CHANNEL_ID = "main";

    Context context_;

    public NotificationManager(Context context) {
        this.context_ = context;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        ((android.app.NotificationManager) context_.getSystemService(Context.NOTIFICATION_SERVICE))
                .createNotificationChannel(new NotificationChannel("main",
                        "main name",
                        android.app.NotificationManager.IMPORTANCE_HIGH));
    }

    public void createNotification(String title, String body) {
        Log.d("mylog", "got new notification");
        NotificationCompat.Builder my_notification = new NotificationCompat.Builder(context_, DEFAULT_CHANNEL_ID)
                .setSmallIcon(R.drawable.curlnotificationtransparent)
                .setLargeIcon(BitmapFactory.decodeResource(context_.getResources(), R.drawable.curlnotificationtransparent))
                .setContentTitle(title)
                .setContentText(body)
                .setColor(0xff3c9cfc);
        NotificationManagerCompat.from(context_).notify((int) Math.ceil(100000 * Math.random()), my_notification.build());
    }
}
