package com.example.a02_04_2025;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Comparator;

public class SqlHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQL";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "BaiHat";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String SINGER = "SINGER";
    private static final String TIME = "TIME";

    private SQLiteDatabase sqLiteDatabase;

    public SqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT NOT NULL, "
                + SINGER + " TEXT NOT NULL, "
                + TIME + " FLOAT NOT NULL" + " ) ";
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
        if(sqLiteDatabase != null && sqLiteDatabase.isOpen())
            sqLiteDatabase.close();

        super.close();
    }

    public long insertTable(String name, String singer, float time){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put(NAME, name);
        contentValues.put(SINGER, singer);
        contentValues.put(TIME, time);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<BaiHat> getAllBaiHat(){
        ArrayList<BaiHat> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + TIME , null );

        if(cursor.moveToFirst()){
            do{
                BaiHat baiHat = new BaiHat(cursor.getString(1),
                        cursor.getString(2), cursor.getFloat(3));
                list.add(baiHat);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


}
