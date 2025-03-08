package com.example.voxachat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText etUsername;
    private Button btnContinue;
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("VoxaChatPrefs", Context.MODE_PRIVATE);

        // Check if the user is already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            navigateToHome();
            return; // Prevent further execution
        }

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        btnContinue = findViewById(R.id.btnContinue);
        databaseHelper = new DatabaseHelper(this);

        btnContinue.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();

        if (!isValidUsername(username)) {
            return;
        }

        if (databaseHelper.isUsernameExists(username)) {
            showToast("Username already exists! Try another.");
            return;
        }

        if (databaseHelper.addUsername(username)) {
            saveLoginStatus(username);
            showToast("Username registered successfully!");
            navigateToHome();
        } else {
            showToast("Error registering username!");
        }
    }

    private boolean isValidUsername(String username) {
        if (TextUtils.isEmpty(username)) {
            showToast("Enter a username");
            return false;
        }

        if (username.length() < 3) {
            showToast("Username must be at least 3 characters long");
            return false;
        }

        return true;
    }

    private void saveLoginStatus(String username) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    private void navigateToHome() {
        startActivity(new Intent(Register.this, HomeActivity.class));
        finish();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
