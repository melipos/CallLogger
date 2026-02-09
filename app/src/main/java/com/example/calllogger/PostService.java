package com.example.calllogger;

import android.app.*;
import android.content.Intent;
import android.os.IBinder;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(() -> {
            try {
                String number = intent.getStringExtra("number");
                String state = intent.getStringExtra("state");

                URL url = new URL("http://localhost/cidweb_post/receive.php");
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("POST");
                c.setDoOutput(true);

                String data =
                        "phone=" + number +
                        "&state=" + state +
                        "&device=android";

                OutputStream os = c.getOutputStream();
                os.write(data.getBytes());
                os.close();

                c.getResponseCode();
                c.disconnect();

            } catch (Exception ignored) {}
            stopSelf();
        }).start();

        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


