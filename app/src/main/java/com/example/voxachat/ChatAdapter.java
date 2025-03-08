package com.example.voxachat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<Chat> chatList; // Assuming you want to display only Chat items
    private OnChatClickListener listener;
    private Context context;

    public interface OnChatClickListener {
        void onChatClick(Chat chat);
    }

    public ChatAdapter(ArrayList<Chat> chatList, OnChatClickListener listener, Context context) {
        this.chatList = chatList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false); // Use a single layout for chat items
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.usernameTextView.setText(chat.getUsername());
        holder.lastMessageTextView.setText(chat.getLastMessage());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChatClick(chat);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatList != null ? chatList.size() : 0;
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView usernameTextView;
        TextView lastMessageTextView;

        ChatViewHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            lastMessageTextView = itemView.findViewById(R.id.lastMessage);
        }
    }
}