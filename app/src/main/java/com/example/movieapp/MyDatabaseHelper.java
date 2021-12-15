package com.example.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.sql.ResultSet;
import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MovieLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_ID";
    private static final String COLUMN_TITLE = "movie_title";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT) ;";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addMovie(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);

        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            //Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public long getNumberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, TABLE_NAME);
    }

    public String getMovie(long id) {
        String query = "SELECT movie_title FROM my_library WHERE _ID = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String rv = "not found";
        if (cursor.moveToFirst()) {
            rv = cursor.getString(0);
        }
        return rv;
    }

    public String getRandomMovie() {
        String query = "SELECT movie_title FROM my_library ORDER BY RANDOM() LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        String movie = cursor.getString(0);
        return movie;
    }

    public ArrayList<Movie> getAllMovies() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM my_library";
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        int id;
        String title;

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                title = cursor.getString((cursor.getColumnIndexOrThrow(COLUMN_TITLE)));

                Movie temp = new Movie(id, title);
                movieList.add(temp);
            }
            while (cursor.moveToNext());
        }
        return movieList;
    }

    public void removeMovie(int Id) {
        Log.i(" did the "," hallå" );
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i(" did the "," hallå" );
        db.delete(TABLE_NAME, "_ID= " + Id, null);
        db.close();
    }


    /*
    public ArrayList<String> getAllMovies() {
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM my_library";
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            array_list.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
            cursor.moveToNext();
        }
        return array_list;
    }

*/
}
