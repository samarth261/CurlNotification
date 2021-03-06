package net.freedynamicdns.samarths.curlnotification;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class NotificationServer {
    private Handler _uihandler;
    private Context _appContext;

    private void ToastIt(final String str) {
        _uihandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(_appContext, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public NotificationServer(Handler uihandle, Context appctx) {
        _uihandler = uihandle;
        _appContext = appctx;
    }

    public void StartListening() {
        try {
            ServerSocket server = new ServerSocket(8090);
            ToastIt("Server Started");
            while (true){
                try {
                    Socket client = server.accept();
                    Thread t = new ClientHandler(client, _appContext);
                    t.start();
                    //t.stop();
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    public void DealWithRequest(Socket client) {

    }
}


class ClientHandler extends Thread {
    private Socket _client;
    DataInputStream _dis;
    DataOutputStream _dos;
    Context _appctx;

    public ClientHandler(Socket client, Context appctx) {
        _client = client;
        try {
            _dis = new DataInputStream(client.getInputStream());
            _dos = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
        }
        _appctx = appctx;
    }

    @Override
    public void run() {
        try {
            String client_input;
            client_input = _dis.readLine();

            if (!client_input.startsWith("GET ")) throw new Exception("not get");
            if (!client_input.endsWith(" HTTP/1.1")) throw new Exception("not http/1.1");
            String query = client_input.substring(5, client_input.length()-9);

            int max_lines_ctr = 100;
            while (--max_lines_ctr > 0) {
                client_input = _dis.readLine();
                if (client_input == null) {
                    throw new Exception("got a null");
                }
                if (client_input.length() == 0) break;
            }
            if (max_lines_ctr == 0) throw new Exception("Max lines reached");

            _dos.writeBytes("HTTP/1.1 200 OK\n");
            _dos.writeBytes("\r\n");
            _dos.writeBytes("got: " + query);
            _dos.flush();
            _client.close();

            String[] nparts = query.split("::");
            if (nparts.length != 2) throw new Exception("Improper length");

            NotificationManager notificationManager = new NotificationManager(_appctx);
            notificationManager.createNotification(nparts[0], nparts[1]);

        } catch (Exception e) {
        }
    }
}