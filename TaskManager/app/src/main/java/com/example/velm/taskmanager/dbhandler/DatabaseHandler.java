package com.example.velm.taskmanager.dbhandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.velm.taskmanager.models.Task;
import com.example.velm.taskmanager.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velm on 9/8/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskmanager";

    private static final String TABLE_USER = "users";
    private static final String TABLE_TASK = "tasks";

    //user table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PIN = "pin";

    //task table column names
    private static final String USER_ID = "user_id";
    private static final String DESCRIPTION = "description";
    private static final String DATE_CREATED = "date_created";
    private static final String DATE_UPDATED = "date_updated";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PIN + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + USER_ID + " INTEGER," + KEY_NAME + " TEXT,"
                + DESCRIPTION + " TEXT," + DATE_CREATED + " TEXT,"
                + DATE_UPDATED + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_TASK_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
        onCreate(sqLiteDatabase);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_PIN, user.getPin());
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User validateUser(String userName, String pin) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                KEY_ID,
                KEY_NAME,
                KEY_PIN
        };
        String selection = KEY_NAME + " = ?" + " AND " + KEY_PIN + " = ?";
        String[] selectionArgs = {userName, pin};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                user = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        return user;
    }


    public boolean checkUser(String userName) {

        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = KEY_NAME + " = ?";

        String[] selectionArgs = {userName};
        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    // Create new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, task.getUserId());
        values.put(KEY_NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE_CREATED, task.getDateCreated());
        values.put(DATE_UPDATED, task.getDateUpdated());

        db.insert(TABLE_TASK, null, values);
        db.close();
    }

    // Update task
    public int updateTask(Task task) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE_CREATED, task.getDateCreated());
        values.put(DATE_UPDATED, task.getDateUpdated());

        // updating row
        return db.update(TABLE_TASK, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});

    }

    //delete task by Id
    public void deleteTask(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();

    }

    // Getting All Tasks
    public List<Task> getAllTasks(int userId) {

        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASK, new String[]{KEY_ID, USER_ID,
                        KEY_NAME, DESCRIPTION, DATE_CREATED, DATE_UPDATED}, USER_ID + "=?",
                new String[]{String.valueOf(userId)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }
}
