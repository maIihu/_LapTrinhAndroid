package com.example.androidtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "TEST_SQLITE";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "TEST";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String YEAR_OF_BIRTH = "YOB";
    private SQLiteDatabase sqLiteDatabase;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT NOT NULL, "
                + YEAR_OF_BIRTH + " INTEGER NOT NULL" + ")";
        db.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public synchronized void close() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
        super.close();
    }

    public void openDB(){
        sqLiteDatabase = getWritableDatabase();
    }

    public void closeDB(){
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()){
            sqLiteDatabase.close();
        }
    }

    public long Insert(String name, int yearOfBirth){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put(NAME, name);
        contentValues.put(YEAR_OF_BIRTH, yearOfBirth);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor displayAll(){
        String query = "select * from " + TABLE_NAME;
        return sqLiteDatabase.rawQuery(query, null);
    }

    public static int getVERSION() {
        return VERSION;
    }

    public SQLiteDatabase getSqLiteDatabase() {
        return sqLiteDatabase;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static String getID() {
        return ID;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getYearOfBirth() {
        return YEAR_OF_BIRTH;
    }

}
