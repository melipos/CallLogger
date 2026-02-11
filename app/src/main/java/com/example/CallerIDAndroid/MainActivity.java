package com.example.CallerIDAndroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.PrintWriter;
import java.net.Socket;

// Kendi projenin R sınıfı
import com.example.CallerIDAndroid.R;

public class MainActivity extends AppCompatActivity {

    // Delphi PC IP adresi ve port
    private static final String DELPHI_IP = "192.168.1.100"; // kendi PC LAN IP
    private static final int DELPHI_PORT = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buton oluştur veya layout’da ekli buton
        Button btnSendTest = findViewById(R.id.btnSendTest);

        btnSendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTestNumber("5551234567"); // test numarası
            }
        });
    }

    // TCP client ile numara gönder
    private void sendTestNumber(final String number) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(DELPHI_IP, DELPHI_PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(number); // numarayı gönder
                socket.close();

                runOnUiThread(() ->
                        Toast.makeText(MainActivity.this, "Numara gönderildi", Toast.LENGTH_SHORT).show()
                );

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() ->
                        Toast.makeText(MainActivity.this, "Hata: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
            }
        }).start();
    }
}

