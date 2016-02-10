package com.simplelilfe.focustodo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by amitt.
 */
public class DatabaseAdapter {

    private ToDoDbHelper toDoDbHelper;

    public DatabaseAdapter(Context context) {
        toDoDbHelper = new ToDoDbHelper(context);
    }

    static class ToDoDbHelper extends SQLiteOpenHelper {

        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ", ";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + ToDoContract.TodoEntry.TABLE_NAME + " (" +
                        ToDoContract.TodoEntry._ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                        ToDoContract.TodoEntry.COLUMN_NAME_TITLE + TEXT_TYPE +
                        " )";

        // TODO If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "ToDo.db";

        public ToDoDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d("onCreate: ", SQL_CREATE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO later
        }
    }

    public void createToDoEntry(String stringToDoEntry) {
        SQLiteDatabase sqLiteDatabase = toDoDbHelper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();

            contentValues.put(ToDoContract.TodoEntry.COLUMN_NAME_TITLE, stringToDoEntry);
            sqLiteDatabase.insert(ToDoContract.TodoEntry.TABLE_NAME, null, contentValues);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }
    }
}
