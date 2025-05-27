package com.example.khachsan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SqlLite extends SQLiteOpenHelper {
    private static final String DB_NAME = "SQL";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "HoaDon";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DATE_TIME = "DATE_TIME";
    private static final String COST = "COST";
    private static final String TIME_LIMIT = "TIME_LIMIT";
    private SQLiteDatabase sqLiteDatabase;

    public SqlLite(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID + " TEXT NOT NULL, "
                + NAME + " TEXT NOT NULL, "
                + DATE_TIME + " TEXT NOT NULL, "
                + COST + " INTEGER NOT NULL, "
                + TIME_LIMIT + " INTEGER NOT NULL"
                + " )";
        db.execSQL(QUERY);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse("22/05/2024");
            insertNew(db, "Vu Truong An", date, 220000, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private String generateNewId(SQLiteDatabase db) {
        String query = "SELECT " + ID + " FROM " + TABLE_NAME + " ORDER BY " + ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        int newNumber = 11; // Mặc định nếu chưa có hóa đơn nào

        if (cursor.moveToFirst()) {
            String lastId = cursor.getString(cursor.getColumnIndexOrThrow(ID)); // ví dụ: HD20
            try {
                int lastNumber = Integer.parseInt(lastId.replaceAll("\\D+", "")); // lấy 20 từ HD20
                newNumber = lastNumber + 9; // tăng thêm 9
            } catch (NumberFormatException e) {
                e.printStackTrace(); // đề phòng ID sai định dạng
            }
        }

        cursor.close();
        return "HD" + newNumber;
    }


    private void insertNew(SQLiteDatabase db, String name, Date dateTime, double cost, int timeLimit) {
        String newId = generateNewId(db);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, newId);
        contentValues.put(NAME, name);
        contentValues.put(DATE_TIME, new SimpleDateFormat("yyyy-MM-dd").format(dateTime));
        contentValues.put(COST, cost);
        contentValues.put(TIME_LIMIT, timeLimit);

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

    public long insertTable(String name, Date dateTime, double cost, int timeLimit){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        contentValues.put(NAME, name);
        contentValues.put(DATE_TIME, String.valueOf(dateTime));
        contentValues.put(COST, String.valueOf(cost));
        contentValues.put(TIME_LIMIT, String.valueOf(timeLimit));
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<HoaDon> getList(){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + NAME , null);

        if(cursor.moveToFirst()){
            do{
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                String dateString = cursor.getString(cursor.getColumnIndexOrThrow(DATE_TIME));
                double cost = cursor.getInt(cursor.getColumnIndexOrThrow(COST));
                int timeLimit = cursor.getInt(cursor.getColumnIndexOrThrow(TIME_LIMIT));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }


                HoaDon hoaDon = new HoaDon(id, name, date, cost, timeLimit);
                list.add(hoaDon);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
