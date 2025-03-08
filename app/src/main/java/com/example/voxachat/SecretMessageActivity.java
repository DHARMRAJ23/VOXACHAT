package com.example.voxachat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SecretMessageActivity extends AppCompatActivity {

    private TextView messageTextView, countdownTextView;
    private CardView messageCard;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis; // Time left in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret_message);

        messageTextView = findViewById(R.id.messageTextView);
        countdownTextView = findViewById(R.id.countdownTextView);
        messageCard = findViewById(R.id.messageCard);

        // Get message and expiration time from Intent
        Intent intent = getIntent();
        String secretMessage = intent.getStringExtra("secret_message");
        long expirationTime = intent.getLongExtra("expiration_time", System.currentTimeMillis() + 60000); // Default 60s

        messageTextView.setText(secretMessage);

        // Calculate remaining time
        timeLeftInMillis = expirationTime - System.currentTimeMillis();
        if (timeLeftInMillis > 0) {
            startCountdownTimer(timeLeftInMillis);
        } else {
            expireMessage();
        }
    }

    private void startCountdownTimer(long millisUntilFinished) {
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }

            public void onFinish() {
                expireMessage();
            }
        }.start();
    }

    private void updateCountdownText() {
        int seconds = (int) (timeLeftInMillis / 1000);
        countdownTextView.setText("Message expires in: " + seconds + "s");
    }

    private void expireMessage() {
        messageTextView.setText("⚠️ This message has expired!");
        countdownTextView.setText("Expired");
        messageCard.setCardBackgroundColor(Color.parseColor("#FF4444")); // Change card color on expiry
        Toast.makeText(this, "Message expired!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
