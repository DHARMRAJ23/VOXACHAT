package com.example.voxachat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CallActivity extends AppCompatActivity {

    private TextView callStatus;
    private Button endCallButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        callStatus = findViewById(R.id.callStatus);
        endCallButton = findViewById(R.id.endCallButton);

        // Simulate a call status
        callStatus.setText("Calling...");

        endCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endCall();
            }
        });
    }

    private void endCall() {
        callStatus.setText("Call Ended");
        endCallButton.setEnabled(false);
    }
}