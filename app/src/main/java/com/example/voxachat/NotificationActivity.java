package com.example.voxachat;

import android.app.Notification;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ListView notificationListView;
    private NotificationAdapter notificationAdapter;
    private ArrayList<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationListView = findViewById(R.id.notificationListView);
        notifications = new ArrayList<>();

        // Load notifications (this should be replaced with actual data fetching)
        loadNotifications();

        notificationAdapter = new NotificationAdapter(this, notifications);
        notificationListView.setAdapter((ListAdapter) notificationAdapter);
    }

    private void loadNotifications() {
        // Dummy data for notifications
        notifications.add(new Notification());
        notifications.add(new Notification());
        // Add more notifications as needed
    }
}