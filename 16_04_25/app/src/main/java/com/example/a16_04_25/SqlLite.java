package com.example.a16_04_25;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqlLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQL";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Contact";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private SQLiteDatabase sqLiteDatabase;

    public SqlLite(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " INTEGER PRIMARY KEY, "
                + NAME + " TEXT NOT NULL, "
                + PHONE_NUMBER + " TEXT NOT NULL"
                + " )";


        db.execSQL(QUERY);

        insertContact(db, 1, "Nam", "0123456789");
        insertContact(db, 2, "Huu Thang", "0123456781");
        insertContact(db,3, "Toan", "0123456782");
        insertContact(db, 4, "Thanh Moi", "0123456783");
        insertContact(db, 5, "Minh Hieu", "0123456784");
        insertContact(db, 6, "Hoang Anh", "0123456785");
    }

    private void insertContact(SQLiteDatabase db, int id, String name, String number){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(PHONE_NUMBER, number);
        db.insert(TABLE_NAME, null, contentValues);
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

    public long insertTable(int id, String name, String phone_number){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(PHONE_NUMBER, phone_number);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Contact> getList(){
        ArrayList<Contact> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + NAME , null );

        if(cursor.moveToFirst()){
            do{
                Contact baiHat = new Contact(cursor.getInt(0), cursor.getString(1),
                        cursor.getString(2));
                list.add(baiHat);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
