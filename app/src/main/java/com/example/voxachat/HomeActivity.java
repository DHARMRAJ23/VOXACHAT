package com.example.voxachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;
    private ArrayList<Chat> chatList;
    private ImageView addFriend, storyButton, callButton, notificationButton; // Changed from ImageButton to ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI components
        initializeViews();

        // Setup RecyclerView
        setupRecyclerView();

        // Populate chat list with sample data
        populateChatList();

        // Setup button click listeners
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.messageList);
        // Make sure this ID exists in XML as ImageView
    }

    private void setupRecyclerView() {
        chatList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chatAdapter = new ChatAdapter(chatList, chat -> {
            Intent intent = new Intent(HomeActivity.this, ChatActivity.class);
            intent.putExtra("username", chat.getUsername());
            startActivity(intent);
        }, this);

        recyclerView.setAdapter(chatAdapter);
    }

    private void populateChatList() {
        chatList.add(new Chat("User 1", "Hello!"));
        chatList.add(new Chat("User 2", "How are you?"));
        chatList.add(new Chat("User 3", "Can you help me, please?"));
        chatList.add(new Chat("User 4", "Hey Friend"));
        chatList.add(new Chat("User 5", "When you come, please bring my books"));
        chatList.add(new Chat("User 6", "Hi Bro, How Are you?"));
        chatList.add(new Chat("User 7", ".... !!"));
        chatList.add(new Chat("User 8", "Hey, when will you come home?"));
        chatList.add(new Chat("User 9", ".."));
        chatList.add(new Chat("User 10", "Hyy"));
        chatList.add(new Chat("User 11", "Chat cleared......"));
        chatList.add(new Chat("User 12", "Disappeared........"));

        // Notify adapter about data changes
        chatAdapter.notifyDataSetChanged();
    }

}
