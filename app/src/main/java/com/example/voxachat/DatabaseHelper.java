package com.example.voxachat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "VoxaChat.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_USERS = "users";
    private static final String TABLE_FRIEND_REQUESTS = "friend_requests";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_REQUEST_ID = "request_id";
    private static final String COLUMN_SENDER = "sender";
    private static final String COLUMN_RECEIVER = "receiver";
    private static final String COLUMN_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT UNIQUE)";

        String createRequestsTable = "CREATE TABLE " + TABLE_FRIEND_REQUESTS + " ("
                + COLUMN_REQUEST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_SENDER + " TEXT, "
                + COLUMN_RECEIVER + " TEXT, "
                + COLUMN_STATUS + " TEXT DEFAULT 'Pending', "
                + "FOREIGN KEY (" + COLUMN_SENDER + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USERNAME + "), "
                + "FOREIGN KEY (" + COLUMN_RECEIVER + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_USERNAME + "))";

        db.execSQL(createUsersTable);
        db.execSQL(createRequestsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIEND_REQUESTS);
        onCreate(db);
    }

    public boolean isUsernameExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public boolean addUsername(String username) {
        if (isUsernameExists(username)) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        long result = db.insert(TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME + " FROM " + TABLE_USERS, null);
        while (cursor.moveToNext()) {
            usernames.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return usernames;
    }

    public boolean sendFriendRequest(String sender, String receiver) {
        if (sender.equals(receiver) || isFriendRequestExists(sender, receiver)) {
            return false;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SENDER, sender);
        values.put(COLUMN_RECEIVER, receiver);
        long result = db.insert(TABLE_FRIEND_REQUESTS, null, values);
        db.close();
        return result != -1;
    }

    public boolean isFriendRequestExists(String sender, String receiver) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT 1 FROM " + TABLE_FRIEND_REQUESTS + " WHERE " + COLUMN_SENDER + " = ? AND " + COLUMN_RECEIVER + " = ?", new String[]{sender, receiver});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public ArrayList<String> getFriendRequests(String receiver) {
        ArrayList<String> requests = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_SENDER + " FROM " + TABLE_FRIEND_REQUESTS + " WHERE " + COLUMN_RECEIVER + " = ? AND " + COLUMN_STATUS + " = 'Pending'", new String[]{receiver});
        while (cursor.moveToNext()) {
            requests.add(cursor.getString(0));
        }
        cursor.close();
        db.close();
        return requests;
    }
}
