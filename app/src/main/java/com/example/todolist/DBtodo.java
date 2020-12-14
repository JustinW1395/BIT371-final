package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBtodo extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "To do List";
    public static final int VERSION = 1;
    public static final String TABLE_NAME = "ToDo";
    public static final String ID_COL = "_id";
    public static final String DONE_COL = "Done";
    public static final String DATE_COL = "Date";
    public static final String ITEM_COL = "Item";

    public DBtodo(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                ID_COL  + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ITEM_COL + " TEXT," +
                DATE_COL + " TEXT," +
                DONE_COL + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
//update the table
    public void setDone(int id, boolean done){
        ContentValues cv = new ContentValues();
        cv.put(DONE_COL, done);
        getWritableDatabase().update(TABLE_NAME, cv, "_id=?", new String[]{Integer.toString(id)});
    }
//add to table
    public int InsertItem(String item, String date){
        ContentValues cv = new ContentValues();
        cv.put(ITEM_COL, item);
        cv.put(DATE_COL,date);
        cv.put(DONE_COL, false);
        return  (int) getWritableDatabase().insert(TABLE_NAME, null, cv);
    }
//get everything
    public ArrayList<ToDoItem> getAllItems(){
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, new String[]{ID_COL, ITEM_COL, DATE_COL, DONE_COL},
                null,
                null,
                null, null, null);
        ArrayList<ToDoItem> items = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                ToDoItem item = new ToDoItem(
                        cursor.getInt(cursor.getColumnIndex(ID_COL)),
                        cursor.getString(cursor.getColumnIndex(ITEM_COL)),
                        cursor.getString(cursor.getColumnIndex(DATE_COL)),
                        cursor.getInt(cursor.getColumnIndex(DONE_COL)) == 1
                );
                items.add(item);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }
}
