package com.example.voxachat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 6000; // Total time before moving to MainActivity
    private static final int ROTATION_DURATION = 1000; // Rotation duration in milliseconds
    private static final int PAUSE_DURATION = 1000; // Pause after each rotation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);

        // Rotate logo in a loop with pauses
        rotateLogo(logo);

        // Move to MainActivity after splash time
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, Register.class));
            finish();
        }, SPLASH_TIME);
    }

    private void rotateLogo(ImageView logo) {
        Handler handler = new Handler();
        Runnable rotateRunnable = new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotate = new RotateAnimation(0, 360,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(ROTATION_DURATION);
                rotate.setFillAfter(true);
                logo.startAnimation(rotate);

                // Wait for rotation to complete, then wait 1 second before starting again
                handler.postDelayed(this, ROTATION_DURATION + PAUSE_DURATION);
            }
        };

        // Start first rotation
        handler.post(rotateRunnable);
    }
}
