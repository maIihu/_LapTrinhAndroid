package com.example.hoadon03;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hoadons_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "hoadons";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_LIMIT_DATE = "limit_date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT, "
                + COLUMN_COST + " DOUBLE, "
                + COLUMN_LIMIT_DATE + " INTEGER "
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    private String generateNewId(SQLiteDatabase db) {
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);

        int newNumber = 14; // Mặc định nếu chưa có hóa đơn nào, ngày sinh

        if (cursor.moveToFirst()) {
            String lastId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)); // ví dụ: HD20
            try {
                int lastNumber = Integer.parseInt(lastId.replaceAll("\\D+", "")); // lấy 20 từ HD20
                newNumber = lastNumber + 12; // tăng thêm 12, tháng sinh
            } catch (NumberFormatException e) {
                e.printStackTrace(); // đề phòng ID sai định dạng
            }
        }

        cursor.close();
        return "HD" + newNumber;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thêm mới
    public void add(HoaDon HoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, generateNewId(db)); // Sinh id mới
        values.put(COLUMN_NAME, HoaDon.getName());
        values.put(COLUMN_DATE, HoaDon.getDate());
        values.put(COLUMN_COST, HoaDon.getCost());
        values.put(COLUMN_LIMIT_DATE, HoaDon.getLimitDate());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Thêm danh sách
    public void addAll(List<HoaDon> HoaDons) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (HoaDon HoaDon : HoaDons) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, HoaDon.getId());
            values.put(COLUMN_NAME, HoaDon.getName());
            values.put(COLUMN_DATE, HoaDon.getDate());
            values.put(COLUMN_COST, HoaDon.getCost());
            values.put(COLUMN_LIMIT_DATE, HoaDon.getLimitDate());

            db.insert(TABLE_NAME, null, values);
        }
        db.close();
    }

    // Cập nhật thông tin liên hệ
    public int updateContact(HoaDon HoaDon) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, HoaDon.getName());
        values.put(COLUMN_NAME, HoaDon.getName());
        values.put(COLUMN_DATE, HoaDon.getDate());
        values.put(COLUMN_COST, HoaDon.getCost());
        values.put(COLUMN_LIMIT_DATE, HoaDon.getLimitDate());

        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[] { HoaDon.getId() });
    }

    // Xóa liên hệ
    public void delete(String studentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { studentId });
        db.close();
    }

    // Lấy danh sách tất cả liên hệ
    @SuppressLint("Range")
    public List<HoaDon> getAll() {
        List<HoaDon> HoaDons = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HoaDon HoaDon = new HoaDon();
                HoaDon.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                HoaDon.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                HoaDon.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                HoaDon.setCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)));
                HoaDon.setLimitDate(cursor.getInt(cursor.getColumnIndex(COLUMN_LIMIT_DATE)));
                HoaDons.add(HoaDon);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return HoaDons;
    }

    // Lấy thông tin 1 liên hệ
    @SuppressLint("Range")
    public HoaDon get(String studentId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
                        COLUMN_ID, COLUMN_NAME, COLUMN_DATE, COLUMN_COST, COLUMN_LIMIT_DATE},
                COLUMN_ID + "=?", new String[] { studentId }, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            HoaDon HoaDon = new HoaDon();
            HoaDon.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
            HoaDon.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            HoaDon.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
            HoaDon.setCost(cursor.getDouble(cursor.getColumnIndex(COLUMN_COST)));
            HoaDon.setLimitDate(cursor.getInt(cursor.getColumnIndex(COLUMN_LIMIT_DATE)));
            cursor.close();
            db.close();
            return HoaDon;
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
