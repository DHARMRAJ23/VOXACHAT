package com.example.voxachat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView tvUsername, tvPosts, tvFriends;
    private MaterialButton btnEditProfile, btnAddFriend;
    private RecyclerView recyclerFriends;
    private FriendsAdapter friendsAdapter;
    private List<String> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialize UI elements
        profileImage = findViewById(R.id.profileImage);
        tvUsername = findViewById(R.id.tvUsername);
        tvPosts = findViewById(R.id.tvPosts);
        tvFriends = findViewById(R.id.tvFriends);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnAddFriend = findViewById(R.id.btnAddFriend);

        // Sample Data for Username & Counts
        tvUsername.setText("John Doe");
        tvPosts.setText("Posts: 10");
        tvFriends.setText("Friends: 5");

        // Set up RecyclerView for Friends List

        // Edit Profile Button Click
        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(ProfileActivity.this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        );

        // Add Friend Button Click
        btnAddFriend.setOnClickListener(v ->
                Toast.makeText(ProfileActivity.this, "Add Friend Clicked", Toast.LENGTH_SHORT).show()
        );

        // Initialize Google Maps

    }


}
