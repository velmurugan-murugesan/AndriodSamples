package com.example.velm.ds;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by velm on 9/8/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wordmanager";

    private static final String TABLE_WORD = "words";

    private static final String TABLE_DOMAINS = "domains";


    //user table column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "word";
    private static final String KEY_COUNT = "count";
    private static final String KEY_DOMAIN_ID = "domain_id";





    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_WORD + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_COUNT + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_DOMAINS_TABLE = "CREATE TABLE " + TABLE_DOMAINS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DOMAIN_ID + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_DOMAINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WORD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOMAINS);
        onCreate(sqLiteDatabase);
    }



    public void addUser(Words words) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, words.getWord());
        values.put(KEY_COUNT, words.getWord().length());
        db.insert(TABLE_WORD, null, values);
        db.close();
    }

    public void addDomains(String domain, int domainId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, domain);
        values.put(KEY_DOMAIN_ID, domainId);
        db.insert(TABLE_DOMAINS, null, values);
        db.close();
    }


    /*public boolean checkUser(String userName) {

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
    }*/


    // Create new task
    /*public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_ID, task.getUserId());
        values.put(KEY_NAME, task.getName());
        values.put(DESCRIPTION, task.getDescription());
        values.put(DATE_CREATED, task.getDateCreated());
        values.put(DATE_UPDATED, task.getDateUpdated());

        db.insert(TABLE_TASK, null, values);
        db.close();
    }*/

    /*// Update task
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

    }*/

    /*//delete task by Id
    public void deleteTask(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASK, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();

    }
*/
    // Getting All Tasks
    public List<Words> getAllWords() {

        List<Words> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORD, new String[]{KEY_NAME},null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Words task = new Words(cursor.getString(0));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }


    public List<Words> getWordsByLetterCount(int count){
        List<Words> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORD, new String[]{
                        KEY_NAME}, KEY_COUNT + "=?",
                new String[]{String.valueOf(count)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Words task = new Words(cursor.getString(0));
                taskList.add(task);
            } while (cursor.moveToNext());
        }
        return taskList;
    }
    // Getting All Tasks
    public List<String> getAllDomains(int domainId) {

        List<String> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOMAINS, new String[]{
                        KEY_NAME}, KEY_DOMAIN_ID + "=?",
                new String[]{String.valueOf(domainId)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                taskList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        return taskList;
    }
}
