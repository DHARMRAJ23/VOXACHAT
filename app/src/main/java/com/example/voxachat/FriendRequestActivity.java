package com.example.voxachat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FriendRequestActivity extends AppCompatActivity {

    private EditText usernameInput;
    private Button sendRequestButton;
    private RecyclerView requestList;
    private RequestAdapter requestAdapter;
    private ArrayList<String> requests;
    private DatabaseHelper databaseHelper;
    private String currentUsername = "Parth_029"; // Replace with the logged-in user’s name

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_request);

        // Initialize views
        usernameInput = findViewById(R.id.usernameInput);
        sendRequestButton = findViewById(R.id.sendRequestButton);
        requestList = findViewById(R.id.requestList);
        databaseHelper = new DatabaseHelper(this);

        // Check if views are correctly initialized
        if (usernameInput == null || sendRequestButton == null || requestList == null) {
            Toast.makeText(this, "Error: Some UI elements are not found!", Toast.LENGTH_LONG).show();
            return;
        }

        // Check if databaseHelper is initialized properly
        if (databaseHelper == null) {
            Toast.makeText(this, "Database Error!", Toast.LENGTH_LONG).show();
            return;
        }

        // Load received friend requests
        requests = databaseHelper.getFriendRequests(currentUsername);
        if (requests == null) {
            requests = new ArrayList<>();
        }

        requestAdapter = new RequestAdapter(requests);
        requestList.setLayoutManager(new LinearLayoutManager(this));
        requestList.setAdapter(requestAdapter);

        // Send friend request button click listener
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usernameInput != null) {
                    String receiverUsername = usernameInput.getText().toString().trim();

                    if (!receiverUsername.isEmpty()) {
                        sendFriendRequest(receiverUsername);
                    } else {
                        Toast.makeText(FriendRequestActivity.this, "Please enter a username", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FriendRequestActivity.this, "Error: Username input field is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendFriendRequest(String receiverUsername) {
        if (databaseHelper == null) {
            Toast.makeText(this, "Database Error!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ensure username exists
        ArrayList<String> allUsers = databaseHelper.getAllUsernames();
        if (allUsers == null || !allUsers.contains(receiverUsername)) {
            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send request
        boolean success = databaseHelper.sendFriendRequest(currentUsername, receiverUsername);
        if (success) {
            Toast.makeText(this, "Friend request sent to " + receiverUsername, Toast.LENGTH_SHORT).show();
            usernameInput.setText(""); // Clear input field
        } else {
            Toast.makeText(this, "Friend request already sent!", Toast.LENGTH_SHORT).show();
        }
    }
}
