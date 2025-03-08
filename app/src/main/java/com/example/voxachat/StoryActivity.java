package com.example.voxachat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity {

    private RecyclerView storyRecyclerView;
    private StoryAdapter storyAdapter;
    private ArrayList<Story> stories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyRecyclerView = findViewById(R.id.storyRecyclerView);
        stories = new ArrayList<>();

        // Load stories (this should be replaced with actual data fetching)
        loadStories();

        storyAdapter = new StoryAdapter(stories);
        storyRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        storyRecyclerView.setAdapter(storyAdapter);
    }

    private void loadStories() {
        // Dummy data for stories
        stories.add(new Story("User 1", "url_to_image1"));
        stories.add(new Story("User 2", "url_to_image2"));
        // Add more stories as needed
    }
}