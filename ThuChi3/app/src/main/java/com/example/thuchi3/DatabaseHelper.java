package com.example.thuchi3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contact_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CONTACTS = "ThuChis";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_COST = "cost";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + COLUMN_ID + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT, "
                + COLUMN_COST + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Thêm mới
    public void add(ThuChi ThuChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, ThuChi.getId());
        values.put(COLUMN_NAME, ThuChi.getName());
        values.put(COLUMN_COST, ThuChi.getCost());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    // Thêm danh sách
    public void addAll(List<ThuChi> ThuChis) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (ThuChi ThuChi : ThuChis) {
            ContentValues values = new ContentValues();

            values.put(COLUMN_ID, ThuChi.getId());
            values.put(COLUMN_NAME, ThuChi.getName());
            values.put(COLUMN_COST, ThuChi.getCost());

            db.insert(TABLE_CONTACTS, null, values);
        }
        db.close();
    }

    // Cập nhật thông tin liên hệ
    public int updateContact(ThuChi ThuChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, ThuChi.getId());
        values.put(COLUMN_NAME, ThuChi.getName());
        values.put(COLUMN_COST, ThuChi.getCost());

        return db.update(TABLE_CONTACTS, values, COLUMN_ID + " = ?",
                new String[] { ThuChi.getId() });
    }

    // Xóa liên hệ
    public void delete(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COLUMN_ID + " = ?", new String[] { studentId });
        db.close();
    }

    // Lấy danh sách tất cả liên hệ
    @SuppressLint("Range")
    public List<ThuChi> getAll() {
        List<ThuChi> ThuChis = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ThuChi ThuChi = new ThuChi();
                ThuChi.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                ThuChi.setName(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME)));
                ThuChi.setCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)));
                ThuChis.add(ThuChi);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return ThuChis;
    }

    // Lấy thông tin 1 liên hệ
    @SuppressLint("Range")
    public ThuChi get(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { COLUMN_ID,
                        COLUMN_ID, COLUMN_NAME, COLUMN_COST },
                COLUMN_ID + "=?", new String[] { studentId }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            ThuChi ThuChi = new ThuChi();
            ThuChi.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            ThuChi.setName(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME)));
            ThuChi.setCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)));
            cursor.close();
            db.close();
            return ThuChi;
        }

        cursor.close();
        db.close();
        return null;
    }

//    // Cập nhật lại số thứ tự trong database
//    public void updateStudentIds() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        int newId = 1;
//        if (cursor.moveToFirst()) {
//            do {
//                @SuppressLint("Range") String currentStudentId = cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_ID));
//                ContentValues values = new ContentValues();
//                values.put(COLUMN_STUDENT_ID, String.valueOf(newId));
//                db.update(TABLE_CONTACTS, values, COLUMN_STUDENT_ID + " = ?",
//                        new String[] { currentStudentId });
//                newId++;
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        db.close();
//    }
}
