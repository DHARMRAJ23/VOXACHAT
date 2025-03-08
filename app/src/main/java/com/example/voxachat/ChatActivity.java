package com.example.voxachat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    private ArrayList<ChatMessage> messageList; // List of chat messages
    private EditText messageInput;
    private Button sendButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get the username from the intent
        username = getIntent().getStringExtra("username");

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);

        // Initialize message list and adapter
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);

        // Set up the send button click listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageInput.getText().toString().trim(); // Trim whitespace
                if (!messageText.isEmpty()) {
                    // Create a new ChatMessage and add it to the list
                    ChatMessage message = new ChatMessage(username, messageText);
                    messageList.add(message);
                    messageAdapter.notifyItemInserted(messageList.size() - 1);
                    messageInput.setText(""); // Clear the input field
                    recyclerView.scrollToPosition(messageList.size() - 1); // Scroll to the last message
                }
            }
        });
    }
}