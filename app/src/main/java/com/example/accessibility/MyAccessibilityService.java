package com.example.CallerIDAndroid;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "MyAccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null) return;

        // Arayan numarayı veya text içeriğini al
        CharSequence eventText = event.getText() != null && !event.getText().isEmpty() 
                                 ? event.getText().get(0)
                                 : null;

        if (eventText != null) {
            String text = eventText.toString();
            Log.d(TAG, "Received text: " + text);
        }

        // Eğer kaynak node’dan text almak istersen
        AccessibilityNodeInfo source = event.getSource();
        if (source != null) {
            CharSequence nodeText = source.getText(); // Tek CharSequence
            if (nodeText != null) {
                String stringText = nodeText.toString();

                // Listeye eklemek istersen
                List<CharSequence> texts = new ArrayList<>();
                texts.add(stringText);

                // Örnek: log bas
                Log.d(TAG, "Node text: " + stringText);
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.d(TAG, "Accessibility Service Interrupted");
    }
}
